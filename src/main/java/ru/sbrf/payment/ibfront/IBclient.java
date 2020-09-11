package ru.sbrf.payment.ibfront;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.sbrf.payment.common.exchange.Container;

import java.io.*;
import java.util.Properties;

import static java.lang.Thread.*;

public class IBclient /*implements ExchWithServer*/ {
    private String addrHost;
    private String addrIP;
    private Integer port;
    private String protocol;
    private String exchangeDir;


    public IBclient() throws IOException {
        File file = new File("PayProperties.ini");
        Properties properties = new Properties();
//        properties.store(new FileWriter(file),"Test");
        properties.load(new FileReader(file));
        this.addrHost = properties.getProperty("addrHost");
        this.addrIP = properties.getProperty("addrIP");
        this.port = Integer.parseInt(properties.getProperty("port"));
        this.protocol = properties.getProperty("protocol");
        this.exchangeDir = properties.getProperty("exchangeDir");
        pay();
    }

    public void pay() {
    }

    public void pay(String addrHost, String addrIP, int port, String protocol) {
        this.addrHost=addrHost;
        this.addrIP=addrIP;
        this.port=port;
        this.protocol=protocol;
    }

// TODO    @Override
    public <T extends Container> T GetFromTheServer(Container cont, Class<T> valueType ) throws IOException, WaitAnserExeption, ClassNotFoundException {
        // отправляем данные на сервер
        // "Class<T> valueType" - написал чисто как попугай из исходников Jackson-а. Что это означает понимаю слабо
        SendToServer(cont);
        // ждем ответ от сервера (дождаться появления файла *.ToClnt, считать содержимое и грохнуть
        String ansName = exchangeDir+"\\"+valueType.getSimpleName()+".ToClnt"; //getName();
        File ansFile = new File(ansName);
        for (int i=0; i<60; i++) {
            // ждем ответ 60 секунд
            if (ansFile.exists()) {
                ObjectMapper mapper = new ObjectMapper();
                T result = (T) mapper.readValue(ansFile, valueType);
                ansFile.delete();
                return result;
            }
            try {
                sleep(1000);
            } catch (InterruptedException e ) {}
            valueType.getCanonicalName();
            valueType.toString();
            valueType.getSimpleName();
         }
        throw new WaitAnserExeption("Истек таймаут ожидания ответа от сервера.");
    }


// TODO    @Override
    public void SendToServer(Container cont) throws IOException {
        // отправляем данные на сервер
        File sndFile = new File(exchangeDir+"\\" + cont.getClass().getSimpleName() + ".ToSrv");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(sndFile, cont);

//		StringWriter writer = new StringWriter();
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.writeValue(writer, cont);

//		String contName = cont.getClass().getSimpleName(); //getName();
//		FileWriter fw = new FileWriter(exchangeDir+"\\" + contName + ".ToSrv", false);
//		fw.write(writer.toString());
//		fw.close();
    }
}
