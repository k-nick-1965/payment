package ru.sbrf.payment.common.exchange;

import java.io.IOException;
import java.util.Optional;

public interface ExchWithClient {

    <T extends Container> Optional<T> giveFromTheClient(Class<T> valueType) throws ContainerExeption, ClassNotFoundException;

    void sendToClient(Container cont) throws IOException;

}
