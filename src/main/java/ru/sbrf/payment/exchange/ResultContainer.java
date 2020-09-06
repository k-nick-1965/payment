package ru.sbrf.payment.exchange;

import java.io.Serializable;

public class ResultContainer implements Container, Serializable {
    private String code;
    private String hint;

    public ResultContainer(String code, String hint) {
        this.code = code;
        this.hint = hint;
    }

    public String getCode() {
        return code;
    }

    public String getHint() {
        return hint;
    }
}
