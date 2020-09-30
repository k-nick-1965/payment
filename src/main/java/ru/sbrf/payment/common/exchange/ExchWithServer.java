package ru.sbrf.payment.common.exchange;

import java.io.IOException;

public interface ExchWithServer {

    <T extends Container> T giveFromTheServer(Container cont, Class<T> valueType ) throws IOException, WaitAnswerExeption, ClassNotFoundException;

    void sendToServer(Container cont) throws IOException;

}
