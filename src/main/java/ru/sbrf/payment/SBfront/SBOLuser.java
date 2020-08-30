package ru.sbrf.payment.SBfront;

public class SBOLuser implements PaymentAttributes {
    private String clientNumber;
    private String mobileNumber;
    private double summa;
    private int currency;
    private String account;

    @Override
    public String getClientNumber() {
        return clientNumber;
    }

    @Override
    public String getMobileNumber() {
        return this.mobileNumber;
    }

    @Override
    public double getSumma() {
        return this.summa;
    }

    @Override
    public int getCurrency() {
        return this.currency;
    }

    @Override
    public String getAccount() {
        return this.account;
    }
}
