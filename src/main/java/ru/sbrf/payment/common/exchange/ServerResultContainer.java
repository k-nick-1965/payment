package ru.sbrf.payment.common.exchange;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ServerResultContainer extends Container implements Serializable {
    private ExchangeResult code;
    private String hint;

    public ServerResultContainer(String clientNumber, ExchangeResult code, String hint) {
        super(clientNumber);
        this.code = code;
        this.hint = hint;
    }

    // все, что дальше - нужно только для сериализации/десериализации
    public ServerResultContainer() {
        this.code = ExchangeResult.OK;
        this.hint = "";
    }

}
