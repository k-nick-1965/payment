package ru.sbrf.payment.common.exchange;

import java.io.Serializable;
import java.util.ArrayList;

public class ServerAccntContainer extends Container implements Serializable {
    private ExchangeResult code;
    private String hint;
    private ArrayList<String> clientAccounts;

    public ServerAccntContainer(String clientNumber, ArrayList<String> clientAccounts) {
        super(clientNumber);
        this.code = ExchangeResult.OK;
        this.hint = "";
        this.clientAccounts = clientAccounts;
    }

    public ServerAccntContainer(String clientNumber, ExchangeResult code, String hint) {
        super(clientNumber);
        this.code = code;
        this.hint = hint;
        this.clientAccounts = new ArrayList<String>(0);
    }

   public ArrayList<String> getClientAccounts() {
        return clientAccounts;
    }

   public ExchangeResult getCode() { return code; }

   public String getHint() { return hint; }

// все, что дальше - нужно только для сериализации/десериализации

    public ServerAccntContainer() {}

//    public void setCode(ExchangeResult code) { this.code = code; }
//
//    public void setHint(String hint) { this.hint = hint; }
//
//    public void setClientAccounts(ArrayList<String> clientAccounts) { this.clientAccounts = clientAccounts; }

}
