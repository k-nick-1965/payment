package ru.sbrf.payment.exchange;

import java.io.Serializable;
import java.util.ArrayList;

public class AccntContainer implements Container, Serializable {
    private String clientNumber;
    private ArrayList<String> clientAccounts;

    public AccntContainer(String clientNumber, ArrayList<String> clientAccounts) {
        this.clientNumber = clientNumber;
        this.clientAccounts = clientAccounts;
    }

    public String getClientNumber() {
        return clientNumber;
    }

   public ArrayList<String> getClientAccounts() {
        return clientAccounts;
    }

}
