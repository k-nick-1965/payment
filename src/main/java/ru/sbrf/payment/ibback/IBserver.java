package ru.sbrf.payment.ibback;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.sbrf.payment.common.exchange.*;

import java.io.*;
import java.util.*;

import static java.lang.Thread.sleep;

public class IBserver implements ExchWithClient {
    // TODO В этом классе вообще ничего не тестировалось
    private HashMap<String, ArrayList<String>> accountsList; // список счетов клиентов
    private HashSet<Long> usedUNs;  // использованные уникальные номера
    private String exchangeDir;  // каталог обмена

    public static void main(String[] args) throws IOException {
        IBserver ibs = new IBserver();
        ibs.startIBserver();
    }

    public IBserver() {
    }

    public void startIBserver() throws IOException {
        initAccountsList();
        File file = new File("PayProperties.ini");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        this.exchangeDir = properties.getProperty("exchangeDir");

        while (true) {
            performAuthentication ();
            performPayment();

            try { sleep(1000); } catch (InterruptedException e ) {}
        }
    }

    private void performAuthentication () throws IOException {
        // проверка наличия клиента по его номеру
        // отправка списка счетов клиента
        ClientAuthenticContainer cac = null;
        ServerAccntContainer sac = null;
        try {
            cac = GetFromTheClient(ClientAuthenticContainer.class);
            if (cac==null) return; // пакет не обнаружен
        } catch (ContainerExeption ce) {
            sac = new ServerAccntContainer("",1,ce.getMessage());
        }
        if (!accountsList.containsKey(cac.getClientNumber())) {
            sac = new ServerAccntContainer(cac.getClientNumber(),3,"Ошибка. Отсутствующий номер клиента.");
        }
        else {
            ArrayList<String> accnts = accountsList.get(cac.getClientNumber());
            sac = new ServerAccntContainer(cac.getClientNumber(),accnts);
        }
        SendToClient(sac);
    }

    private void performPayment () throws IOException {
        // проверка наличия клиента по его номеру
        // отправка списка счетов клиента
        ClientPaymentContainer cpc = null;
        ServerResultContainer src = null;
        try {
            cpc = GetFromTheClient(ClientPaymentContainer.class);
            if (cpc==null) return; // пакет не обнаружен
        } catch (ContainerExeption ce) {
            src = new ServerResultContainer("",1,ce.getMessage());
        }
        if (!accountsList.containsKey(cpc.getClientNumber())) {
            src = new ServerResultContainer(cpc.getClientNumber(),3,"Ошибка. Отсутствующий номер клиента.");
        }
        else {
            // В этом месте мы проверяем реквизиты платежа
            // и отправляем мобильному оператору
            src = new ServerResultContainer(cpc.getClientNumber(),0,"Платеж отправлен. Надейтесь на лучшее.");
        }
        SendToClient(src);
    }


    @Override
    public <T extends Container> T GetFromTheClient(Class<T> valueType) throws ContainerExeption {
        // загрузка класса из файла пакета
        File inFile = new File(exchangeDir+"\\"+ valueType.getSimpleName()+".ToSrv");
        if (inFile.exists()) {
            ObjectMapper mapper = new ObjectMapper();
            T result;
            try {
                result = (T) mapper.readValue(inFile, valueType);
            } catch (IOException e) {
                throw new ContainerExeption("Ошибка открытия пакета.");
            }
            inFile.delete();
            if  (usedUNs.contains(result.viewUniqueNumber())) {
                throw new ContainerExeption("Ошибка. Повтор пакета.");
            }
            else usedUNs.add(result.viewUniqueNumber());
            return result;
        }
        return null;
   }

    @Override
    public <T extends Container> void SendToClient(Container cont) throws IOException {
        // отправляем данные на клиентскую часть
        File sndFile = new File(exchangeDir+"\\" + cont.getClass().getSimpleName() + ".ToClnt");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(sndFile, cont);
    }

    private void initAccountsList() {
//        List<String> as = new ArrayList(Arrays.asList("a", "b", "c", "d"));
        accountsList.put("4564", new ArrayList<String>(Arrays.asList("40817810000000000000",
                "40817810000000000001",                                                                     "40817810000000000000",
                "40817810000000000002",
                "40817810000000000003")));
        accountsList.put("4545", new ArrayList<String>(Arrays.asList("40817810000000000005",
                "40817810000000000006",                                                                     "40817810000000000000",
                "40817810000000000007",
                "40817810000000000008")));
        accountsList.put("5656", new ArrayList<String>(Arrays.asList("40817810000000000009",
                "40817810000000000010",                                                                     "40817810000000000000",
                "40817810000000000011",
                "40817810000000000012")));
    }

}
