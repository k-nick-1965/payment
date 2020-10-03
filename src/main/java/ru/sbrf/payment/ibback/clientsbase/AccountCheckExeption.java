package ru.sbrf.payment.ibback.clientsbase;

import lombok.Getter;
import ru.sbrf.payment.common.exchange.ExchangeResult;

@Getter
public class AccountCheckExeption extends Exception {
    ExchangeResult code;

    public AccountCheckExeption(String message) {
        super(message);
        this.code=ExchangeResult.OK;
    }

    public AccountCheckExeption(String message, ExchangeResult er) {
        super(message);
        this.code=er;
    }

}

