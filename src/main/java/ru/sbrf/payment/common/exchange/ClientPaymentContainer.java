package ru.sbrf.payment.common.exchange;

import java.io.Serializable;

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

    public String getAccount() {
        return account;
    }

    public Integer getSumma() {
        return summa;
    }

    public Integer getCurrency() {
        return currency;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

// все, что дальше - нужно только для сериализации/десериализации

    public ClientPaymentContainer() {}

}
