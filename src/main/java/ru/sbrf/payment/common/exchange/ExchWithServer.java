package ru.sbrf.payment.common.exchange;

import ru.sbrf.payment.ibfront.WaitAnswerExeption;

import java.io.IOException;

public interface ExchWithServer {

    <T extends Container> T GetFromTheServer(Container cont, Class<T> valueType ) throws IOException, WaitAnswerExeption, ClassNotFoundException;

    void SendToServer(Container cont) throws IOException;

}
