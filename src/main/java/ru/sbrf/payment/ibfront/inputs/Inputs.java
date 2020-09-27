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

//    public abstract boolean validation();
//    public abstract boolean validation(Validator<String> validator);
    public boolean validation(Validator<String> validator) {
        return validator.validation(inputString);
    }
    public abstract boolean validate();
    public abstract Object conversion();

}
