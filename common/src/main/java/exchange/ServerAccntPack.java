package exchange;

import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
public class ServerAccntPack extends Pack implements Serializable {
    private ExchangeResult code;
    private String hint;
    private ArrayList<String> clientAccounts;

    public ServerAccntPack(String clientNumber, ArrayList<String> clientAccounts) {
        super(clientNumber);
        this.code = ExchangeResult.OK;
        this.hint = "";
        this.clientAccounts = clientAccounts;
    }

    public ServerAccntPack(String clientNumber, ExchangeResult code, String hint) {
        super(clientNumber);
        this.code = code;
        this.hint = hint;
        this.clientAccounts = new ArrayList<String>(0);
    }

    // все, что дальше - нужно только для сериализации/десериализации
    public ServerAccntPack() {
        this.code = ExchangeResult.OK;
        this.hint = "";
        this.clientAccounts = new ArrayList<>();
    }

}
