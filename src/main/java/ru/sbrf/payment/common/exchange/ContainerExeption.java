package ru.sbrf.payment.common.exchange;

import lombok.Getter;

@Getter
public class  ContainerExeption extends Exception {
    ExchangeResult code;

    public ContainerExeption(String message) {
        super(message);
        this.code=ExchangeResult.UNKNOWN_ERROR;
    }

    public ContainerExeption(String message, ExchangeResult er) {
        super(message);
        this.code=er;
    }

}

