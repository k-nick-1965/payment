package ru.sbrf.payment.ibfront.inputs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract  class Inputs {
    private String inputString;

    public Inputs (String defaultString) {
        this.inputString=defaultString;
    }

    public abstract boolean validation();
    public abstract Object conversion();

}
