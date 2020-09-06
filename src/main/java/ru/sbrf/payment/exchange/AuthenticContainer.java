package ru.sbrf.payment.exchange;

import java.io.Serializable;

public class AuthenticContainer implements Container, Serializable {
    private String clientNumber;

    public AuthenticContainer(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getClientNumber() {
        return clientNumber;
    }

}
