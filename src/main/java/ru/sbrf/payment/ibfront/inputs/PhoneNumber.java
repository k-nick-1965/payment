package ru.sbrf.payment.ibfront.inputs;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class PhoneNumber extends Inputs{

    public PhoneNumber(String InputString) {
        super(InputString);
    }

//    @Override
//    public boolean validation() {
//        return conversion().length()==10;
//    }

    @Override
    public boolean validate() {
        return conversion().length()==10;
    }

    @Override
    public String conversion() {
        String tempString = super.getInputString().replaceAll("[[^0-9]+]","");
        if (tempString.substring(0,2).equals("79") || tempString.substring(0,2).equals("89")) {
            tempString=tempString.substring(1);
        }
        return tempString;
    }


}
