package ru.sbrf.payment.common.exchange;

import java.io.Serializable;

public class ServerResultContainer extends Container implements Serializable {
    private Integer code;
    private String hint;

    public ServerResultContainer(String clientNumber, Integer code, String hint) {
        super(clientNumber);
        this.code = code;
        this.hint = hint;
    }

    public Integer getCode() {
        return code;
    }

    public String getHint() {
        return hint;
    }

// все, что дальше - нужно только для сериализации/десериализации

    public ServerResultContainer() {}

    public void setCode(Integer code) { this.code = code; }

    public void setHint(String hint) { this.hint = hint; }
}
