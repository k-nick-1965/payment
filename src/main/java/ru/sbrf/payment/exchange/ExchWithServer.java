package ru.sbrf.payment.exchange;

import java.io.IOException;

public interface ExchWithServer {

    Container GetFromTheServer(Container cont);

    void SendToServer(Container cont) throws IOException;

}
