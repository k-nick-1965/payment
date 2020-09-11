package ru.sbrf.payment.common.exchange;

import ru.sbrf.payment.ibfront.WaitAnserExeption;

import java.io.IOException;

public interface ExchWithServer {

    <T extends Container> T GetFromTheServer(Container cont, Class<T> valueType ) throws IOException, WaitAnserExeption, ClassNotFoundException;

    void SendToServer(Container cont) throws IOException;

}
