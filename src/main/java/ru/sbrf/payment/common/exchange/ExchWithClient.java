package ru.sbrf.payment.common.exchange;

import java.io.IOException;

public interface ExchWithClient {

    Container GetFromTheClient(Container cont);

    void SendToClient(Container cont) throws IOException;

}
