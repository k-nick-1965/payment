package ru.sbrf.payment.common.exchange;

import lombok.Getter;
import java.io.Serializable;

@Getter
public class ClientPaymentContainer extends Container implements Serializable {
    private String account;
    private Integer summa;
    private Integer currency;
    private String mobileNumber;

    public ClientPaymentContainer(String clientNumber, String account, Integer summa, Integer currency, String mobileNumber) {
        super(clientNumber);
        this.account = account;
        this.summa = summa;
        this.currency = currency;
        this.mobileNumber = mobileNumber;
    }


    // все, что дальше - нужно только для сериализации/десериализации
    public ClientPaymentContainer() {}

}
