package ru.sbrf.payment.exchange;

import java.io.Serializable;

public class PaymentContainer implements Container, Serializable {
    private String clientNumber;
    private String account;
    private Integer summa;
    private Integer currency;
    private String mobileNumber;

    public PaymentContainer(String clientNumber, String account, Integer summa, Integer currency, String mobileNumber) {
        this.clientNumber = clientNumber;
        this.account = account;
        this.summa = summa;
        this.currency = currency;
        this.mobileNumber = mobileNumber;
    }

    public String getClientNumber() {
        return clientNumber;
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
}
