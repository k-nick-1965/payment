package ibback.clientsbase;

import exchange.ExchangeResult;
import lombok.Getter;

@Getter
public class AccountCheckExeption extends Exception {
    ExchangeResult code;

    public AccountCheckExeption(String message) {
        super(message);
        code= ExchangeResult.UNKNOWN_ERROR;
    }

    public AccountCheckExeption(String message, ExchangeResult er) {
        super(message);
        code=er;
    }

}

