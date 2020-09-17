package ru.sbrf.payment.common.exchange;

public class  ContainerExeption extends Exception {
    ExchangeResult code;

    public ContainerExeption(String message) {
        super(message);
        this.code=ExchangeResult.OK;
    }

    public ContainerExeption(String message,ExchangeResult er) {
        super(message);
        this.code=er;
    }

    public ExchangeResult getCode() {
        return code;
    }
}

