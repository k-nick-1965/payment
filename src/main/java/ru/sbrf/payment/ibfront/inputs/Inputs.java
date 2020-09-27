package ru.sbrf.payment.ibfront.inputs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Inputs{
    private String inputString;

    public Inputs (String defaultString) {
        this.inputString=defaultString;
    }

    public boolean checking(Checker<String> checker) {
        return checker.checking(inputString);
    }
    public abstract boolean check();
    public abstract Object conversion();

}
