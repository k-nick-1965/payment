package ru.sbrf.payment.common.exchange;

import java.io.Serializable;

public class ServerResultContainer extends Container implements Serializable {
    private ExchangeResult code;
    private String hint;

    public ServerResultContainer(String clientNumber, ExchangeResult code, String hint) {
        super(clientNumber);
        this.code = code;
        this.hint = hint;
    }

    public ExchangeResult getCode() {
        return code;
    }

    public String getHint() {
        return hint;
    }

// все, что дальше - нужно только для сериализации/десериализации

    public ServerResultContainer() {}

//    public void setCode(ExchangeResult code) { this.code = code; }
//
//    public void setHint(String hint) { this.hint = hint; }
}
