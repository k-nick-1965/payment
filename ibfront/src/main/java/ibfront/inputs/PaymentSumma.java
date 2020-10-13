package ibfront.inputs;

import java.util.regex.Pattern;

public class PaymentSumma extends Inputs{

    public PaymentSumma(String InputString) {
        super(InputString);
    }

//    @Override
//    public boolean validation() {
//        return Pattern.matches("(\\d+\\.?\\d*)",super.getInputString());
//    }

    @Override
    public boolean check() {
        return super.argChecking((x) -> Pattern.matches("(\\d+\\.?\\d*)",x));
    }

    @Override
    public Long conversion() {
        Double dbl = (Double.parseDouble(super.getInputString()) * 100);
        return dbl.longValue();
    }

}
