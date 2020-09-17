package ru.sbrf.payment.common.exchange;

import java.io.IOException;

public interface ExchWithServer {

    <T extends Container> T GetFromTheServer(Container cont, Class<T> valueType ) throws IOException, WaitAnswerExeption, ClassNotFoundException;

    void SendToServer(Container cont) throws IOException;

}
