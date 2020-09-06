package ru.sbrf.payment.sbfront;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.sbrf.payment.exchange.Container;
import ru.sbrf.payment.exchange.ExchWithServer;

import java.io.*;
import java.util.Properties;

import static java.lang.Thread.*;

public class SBOL implements ExchWithServer {
    private String addrHost;
    private String addrIP;
    private Integer port;
    private String protocol;
    private String exchangeDir;
//    private SBOLuser currentUser;

//    public static void main(String[] args) throws IOException {
//        SBOLuser sbClient = new SBOLuser();
//        sbClient.userInterface();
//
//    }


    public SBOL() throws IOException {
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

    @Override
    public Container GetFromTheServer(Container cont) throws IOException, WaitAnserExeption, ClassNotFoundException {
// отправляем данные на сервер
        SendToServer(cont);
// TODO: здесь нужно получить ответ от сервера (дождаться появления файла *.ToClnt, считать содержимое и грохнуть
// TODO: здесь все закончилось, т.к. Java навернулась с ошибкой "Error:java: Compilation failed: internal java compiler error"
        for (int i=0; i<60; i++) {
            File folder = new File(exchangeDir+"\\");
            if (folder.listFiles().length>0) {
//                String fileName=folder.listFiles((dir, name) -> {
//                                                 return name.toLowerCase().endsWith(".ToClnt");
//                                                })[0].getName();
 //               StringReader reader = new StringReader(jsonString);

                ObjectMapper mapper = new ObjectMapper();
//                Class obj = Class.forName("com.test.classes.MyClass");
//                return (Container) mapper.readValue(reader, Class.forName(fileName.substring(0,fileName.lastIndexOf('.'))));
            }
            try {
                sleep(1000);
            } catch (InterruptedException  e ) {}
        }
        throw new WaitAnserExeption();
    }

    @Override
    public void SendToServer(Container cont) throws IOException {
// отправляем данные на сервер
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, cont);

        String contName = cont.getClass().getName(); //getSimpleName();
        FileWriter fw = new FileWriter(exchangeDir+"\\" + contName + ".ToSrv", false);
        fw.write(writer.toString());
        fw.close();
    }
}
