package ru.sbrf.payment.common.exchange;

import java.io.Serializable;
import java.util.Date;

public class ClientAuthenticContainer extends Container implements Serializable {

    public ClientAuthenticContainer(String clientNumber) {
        super(clientNumber);
    }

    // все, что дальше - нужно только для сериализации/десериализации

    public ClientAuthenticContainer() {
    }

}
