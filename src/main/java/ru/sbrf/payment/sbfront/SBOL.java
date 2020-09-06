package ru.sbrf.payment.sbfront;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.sbrf.payment.exchange.Container;
import ru.sbrf.payment.exchange.ExchWithServer;

import java.io.*;
import java.util.Properties;

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
    public Container GetFromTheServer(Container cont) {
// TODO: здесь нужно отправить запрос на сервер и получить ответ
        return null;
    }

    @Override
    public void SendToServer(Container cont) throws IOException {
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, cont);
        String result = writer.toString();
        System.out.println(result);
// TODO: здесь нужно отправить данные на сервер



    }
}
