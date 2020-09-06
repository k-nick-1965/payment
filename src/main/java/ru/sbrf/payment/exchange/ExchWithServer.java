package ru.sbrf.payment.exchange;

import ru.sbrf.payment.sbfront.WaitAnserExeption;

import java.io.IOException;

public interface ExchWithServer {

    Container GetFromTheServer(Container cont) throws IOException, InterruptedException, WaitAnserExeption, ClassNotFoundException;

    void SendToServer(Container cont) throws IOException;

}
