package ru.sbrf.payment.SBback;

import java.util.HashMap;

public class SBOLserver implements ClientAttributes {
    HashMap<String,String[]> accountsList;

    public static void main(String[] args) {
    }

    public SBOLserver(HashMap<String, String[]> accountsList) {
        this.accountsList = accountsList;       // загрузить список счетов клиентов SBOL
    }

    @Override
    public String[] getClientAccounts(String clientNumber) {
        return accountsList.get(clientNumber);
    }
}
