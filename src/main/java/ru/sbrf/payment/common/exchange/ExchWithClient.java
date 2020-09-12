package ru.sbrf.payment.common.exchange;

import java.io.IOException;

public interface ExchWithClient {

    <T extends Container> T GetFromTheClient(Class<T> valueType) throws ContainerExeption, ClassNotFoundException;

    <T extends Container> void SendToClient(Container cont) throws IOException;

}
