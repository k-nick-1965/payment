package ibback;

import com.fasterxml.jackson.databind.ObjectMapper;

import exchange.*;
import ibback.clientsbase.*;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.*;
import java.security.*;

import static java.lang.Thread.sleep;

public class IBserver implements ExchWithClient {
    private HashSet<Long> usedUNs = new HashSet<>();  // использованные уникальные номера
    private String exchangeDir;  // каталог обмена

    public static void main(String[] args) throws IOException {
        IBserver ibs = new IBserver();
        ibs.startIBserver();
    }

    public IBserver() throws IOException {
        File file = new File("PayProperties.ini");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        this.exchangeDir = properties.getProperty("exchangeDir");
    }

    public void startIBserver() throws IOException {
        while (true) {
            performAuthentication ();
            performPayment();

            try { sleep(1000); } catch (InterruptedException e ) { break; }
        }
    }

    public void testingIBserver() throws IOException {
        performAuthentication ();
        performPayment();
    }

    private void performAuthentication () throws IOException {
        // проверка наличия клиента по его номеру
        // отправка списка счетов клиента
        ClientAuthenticPack cac = null;
        ServerAccntPack sac = null;
        ArrayList<String> accnts;
        Optional<ClientAuthenticPack> optCAC;
        Optional<Integer> optClntID;
        Optional<ArrayList<String>> optAcnts;
        // Я понял, что не нужно создавать переменные с типом Optional,
        // только не знаю как без этого обойтись, если нужно ветвиться по результату.
        // Т.е., в том случае, когда пустой ответ - штатная ситуация.

        // проверяем наличие файла пакета, если есть - закачиваем в ракет
        try {
            if (( optCAC = giveFromTheClient(ClientAuthenticPack.class)).isPresent()) cac=optCAC.get();
            else return; // штатная ситуация - файл пакетаа не обнаружен
        } catch (ContainerExeption ce) {
            // Ошибка - пакет есть но закачать не удалось - помещаем диагостическую информацию в пакета
            sendToClient(new ServerAccntPack("",ce.getCode(),ce.getMessage()));
            return;
        }

        // проводим аутентификацию по номеру клиента и паролю - при положительном результате возвращается ID клиента
        if ((optClntID= ClientItems.authentication(cac.getClientNumber(), cac.getPassWord())).isPresent()) {
            // По ID клиента пытаемся получить список счетов клиента
            if (( optAcnts = AccountItems.giveClientAccounts(optClntID.get())).isPresent()) {
                if (!optAcnts.get().isEmpty()) {
                    // Выгружаем список счетов в пакет
                    sac = new ServerAccntPack(cac.getClientNumber(), optAcnts.get());
                }
                else {
                    sac = new ServerAccntPack(cac.getClientNumber(), ExchangeResult.ACCOUNTS_MISSING,"Ошибка. У клиента отсутствуют счета.");
                }
            }
			else{
                sac = new ServerAccntPack(cac.getClientNumber(),ExchangeResult.ACCOUNTS_MISSING,"Ошибка. У клиента отсутствуют счета.");
            }
        } else {
            sac = new ServerAccntPack(cac.getClientNumber(),ExchangeResult.AUTHENTICATION_ERROR,"Ошибка аутентификации (Неверный идентификатор или пароль).");
        }
        // Выгружаем пакет в файл
        sendToClient(sac);
    }

    private void performPayment() throws IOException {
        // проверка реквизитов платежа
        // отправка подтверждения/ошибки клиенту
        ClientPaymentPack cpc = null;
        ServerResultPack src = null;
        Optional<ClientPaymentPack> opt;
        AccountItem accntItem = null;

        // проверяем наличие файла пакетаа, если есть - закачиваем в пакет
        try {
            if ((opt = giveFromTheClient(ClientPaymentPack.class)).isPresent()) cpc=opt.get();
            else return; // штатная ситуация - файл пакетаа не обнаружен
        } catch (ContainerExeption ce) {
            // Ошибка - пакет есть но закачать не удалось - помещаем диагостическую информацию в пакет
            sendToClient(new ServerResultPack("",ce.getCode(),ce.getMessage()));
            return;
        }

        // для проверки реквизитов платежа нужно получить реквизиты счета
        try {
            accntItem = AccountItems.giveAccountItem(cpc.getAccount()).orElseThrow(() -> new AccountCheckExeption( "Ошибка при обращении к счету.", ExchangeResult.ACCOUNT_ERROR));
        } catch (AccountCheckExeption ace) {
            sendToClient(new ServerResultPack("",ace.getCode(),ace.getMessage()));
            return;
        }

        // проверяем реквизиты платежа (наличие клиента, принадлежность счета, остаток и валюту)
        try {
            accntItem.checkOwner(cpc.getClientNumber())
                    .checkCurrency(cpc.getCurrency())
                    .checkBalance(cpc.getSumma());
        } catch (AccountCheckExeption ace) {
            sendToClient(new ServerResultPack("",ace.getCode(),ace.getMessage()));
            return;
        }

        // Отправка платежа получателю
        sendPaymetToTheMoon(cpc);

        // Инфорирование клиента об исполнении платежа
        sendToClient(new ServerResultPack(cpc.getClientNumber(),ExchangeResult.OK,"Платеж отправлен. Надейтесь на лучшее."));
    }

    private void sendPaymetToTheMoon(ClientPaymentPack cpc) {}

    @Override
    public <T extends Pack> Optional<T> giveFromTheClient(Class<T> valueType) throws ContainerExeption {
        // загрузка класса из файла пакета
        File inFile = new File(exchangeDir+"\\"+ valueType.getSimpleName()+".ToSrv");
        if (inFile.exists()) {
            ObjectMapper mapper = new ObjectMapper();
            T result;
            try {
                result = (T) mapper.readValue(inFile, valueType);
            } catch (IOException e) {
                throw new ContainerExeption("Ошибка открытия пакета.",ExchangeResult.CONTAINER_OPENING_ERROR);
            }
            inFile.delete();
            // Проверяем ID пакета
            if  (usedUNs.contains(result.viewUniqueNumber())) { // такой ID уже был. Это ошибка
                throw new ContainerExeption("Ошибка. Повтор пакета.",ExchangeResult.CONTAINER_DUPLICATE_ERROR);
            }
            else usedUNs.add(result.viewUniqueNumber()); // такого ID не было, добавляем ID пакета в Set
            return Optional.of(result);
        }
        return Optional.empty();
    }

    @Override
    public void sendToClient(@NotNull Pack cont) throws IOException {
        // отправляем данные на клиентскую часть
        File sndFile = new File(exchangeDir+"\\" + cont.getClass().getSimpleName() + ".ToClnt");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(sndFile, cont);
    }

}
