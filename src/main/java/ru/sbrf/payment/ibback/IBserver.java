package ru.sbrf.payment.ibback;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.sbrf.payment.common.exchange.*;

import java.io.*;
import java.util.*;

import static java.lang.Thread.sleep;

public class IBserver implements ExchWithClient {
    private HashMap<String, ArrayList<String>> accountsList = new HashMap<>();; // список счетов клиентов
    private HashSet<Long> usedUNs = new HashSet<>();  // использованные уникальные номера
    private String exchangeDir;  // каталог обмена

    public static void main(String[] args) throws IOException {
        IBserver ibs = new IBserver();
        ibs.startIBserver();
    }

    public IBserver() throws IOException {
        initAccountsList();
        File file = new File("PayProperties.ini");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        this.exchangeDir = properties.getProperty("exchangeDir");
    }

    public void startIBserver() throws IOException {
        while (true) {
            performAuthentication ();
            performPayment();

            try { sleep(1000); } catch (InterruptedException e ) {}
        }
    }

    public void testingIBserver() throws IOException {
        performAuthentication ();
        performPayment();
    }

    private void performAuthentication () throws IOException {
        // проверка наличия клиента по его номеру
        // отправка списка счетов клиента
        ClientAuthenticContainer cac = null;
        ServerAccntContainer sac = null;
        Optional<ClientAuthenticContainer> opt;
        try {
            opt = GetFromTheClient(ClientAuthenticContainer.class);
            if (opt.isPresent()) cac=opt.get();
            else return; // пакет не обнаружен
            // Получилось адски коряво по сравнению с этим:
            // cac = GetFromTheClient(ClientAuthenticContainer.class);
            // if (cac==null) return;
            // видимо чего-то я недопонял
        } catch (ContainerExeption ce) {
            sac = new ServerAccntContainer("",ce.getCode(),ce.getMessage());
        }
        if (!accountsList.containsKey(cac.getClientNumber())) {
            sac = new ServerAccntContainer(cac.getClientNumber(),ExchangeResult.MISSING_USER_NUMBER,"Ошибка. Отсутствующий номер клиента.");
        }
        else {
            ArrayList<String> accnts = accountsList.get(cac.getClientNumber());
            sac = new ServerAccntContainer(cac.getClientNumber(),accnts);
        }
        SendToClient(sac);
    }

    private void performPayment() throws IOException {
        // проверка наличия клиента по его номеру
        // отправка подтверждения/ошибки клиенту
        ClientPaymentContainer cpc = null;
        ServerResultContainer src = null;
        Optional<ClientPaymentContainer> opt;
        try {
            opt = GetFromTheClient(ClientPaymentContainer.class);
            if (opt.isPresent()) cpc=opt.get();
            else return; // пакет не обнаружен
            // cpc = GetFromTheClient(ClientPaymentContainer.class).get();
            // if (cpc==null) return; // пакет не обнаружен
        } catch (ContainerExeption ce) {
            src = new ServerResultContainer("",ce.getCode(),ce.getMessage());
        }
        if (!accountsList.containsKey(cpc.getClientNumber())) {
            src = new ServerResultContainer(cpc.getClientNumber(),ExchangeResult.MISSING_USER_NUMBER,"Ошибка. Отсутствующий номер клиента.");
        }
        else {
            // В этом месте мы якобы проверяем реквизиты платежа
            // и отправляем мобильному оператору
            src = new ServerResultContainer(cpc.getClientNumber(),ExchangeResult.OK,"Платеж отправлен. Надейтесь на лучшее.");
        }
        SendToClient(src);
    }


    @Override
    public <T extends Container> Optional<T> GetFromTheClient(Class<T> valueType) throws ContainerExeption {
        // загрузка класса из файла контейнера
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
    public void SendToClient(Container cont) throws IOException {
        // отправляем данные на клиентскую часть
        File sndFile = new File(exchangeDir+"\\" + cont.getClass().getSimpleName() + ".ToClnt");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(sndFile, cont);
    }

    private void initAccountsList() { // for debuggin
        // Первоначальная загрузка списка клиентов
        //        List<String> as = new ArrayList(Arrays.asList("a", "b", "c", "d"));
        accountsList.put("4564", new ArrayList<String>(Arrays.asList("40817810000000000000",
                "40817810000000000001",
                "40817810000000000002",
                "40817810000000000003")));
        accountsList.put("4545", new ArrayList<String>(Arrays.asList("40817810000000000005",
                "40817810000000000006",
                "40817810000000000007",
                "40817810000000000008")));
        accountsList.put("5656", new ArrayList<String>(Arrays.asList("40817810000000000009",
                "40817810000000000010",
                "40817810000000000011",
                "40817810000000000012")));
    }

}
