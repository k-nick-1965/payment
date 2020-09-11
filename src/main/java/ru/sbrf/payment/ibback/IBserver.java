package ru.sbrf.payment.ibback;

import java.util.HashMap;

public class IBserver implements ClientAttributes {
    HashMap<String,String[]> accountsList;

    public static void main(String[] args) {
    }

    public IBserver(HashMap<String, String[]> accountsList) {
        this.accountsList = accountsList;       // загрузить список счетов клиентов SBOL
    }

    @Override
    public String[] getClientAccounts(String clientNumber) {
        return accountsList.get(clientNumber);
    }
}
