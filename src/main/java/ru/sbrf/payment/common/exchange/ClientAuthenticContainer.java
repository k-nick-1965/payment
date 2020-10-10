package ru.sbrf.payment.common.exchange;

import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
public class ClientAuthenticContainer extends Container implements Serializable {
    private String passWord;

    public ClientAuthenticContainer(String clientNumber) {
        super(clientNumber);
    }

    public ClientAuthenticContainer(String clientNumber, String passWord) {
        super(clientNumber);
        this.passWord = passWord;
    }

    // все, что дальше - нужно только для сериализации/десериализации
    public ClientAuthenticContainer() {
        this.passWord = "";
    }

}
