package exchange;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ServerResultPack extends Pack implements Serializable {
    private ExchangeResult code;
    private String hint;

    public ServerResultPack(String clientNumber, ExchangeResult code, String hint) {
        super(clientNumber);
        this.code = code;
        this.hint = hint;
    }

    // все, что дальше - нужно только для сериализации/десериализации
    public ServerResultPack() {
        this.code = ExchangeResult.OK;
        this.hint = "";
    }

}
