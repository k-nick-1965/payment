package ru.sbrf.payment.common.exchange;

import java.io.IOException;
import java.util.Optional;

public interface ExchWithClient {

    <T extends Container> Optional<T> GetFromTheClient(Class<T> valueType) throws ContainerExeption, ClassNotFoundException;

    void SendToClient(Container cont) throws IOException;

}
