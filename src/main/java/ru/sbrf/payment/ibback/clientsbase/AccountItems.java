package ru.sbrf.payment.ibback.clientsbase;

import java.util.*;
import java.util.stream.Collectors;

public class AccountItems {
    // Это должна быть таблица с полями ID, userID, AccountNumber, Balance
    // Ща буду реализовывать это на коллекциях.
    private static final Map<Integer, AccountItem> accntTable= new HashMap<>();
    private static Integer accntTableID = 0;

    static {  // Помню, что это нехорошо, но, т.к. это эмуляция БД, которую не требуется заполнять, то пойдет.
        accntTable.put(++accntTableID,new AccountItem(4545,"40817810000000000000",1000000L));
        accntTable.put(++accntTableID,new AccountItem(4545,"40817810000000000001",1100000L));
        accntTable.put(++accntTableID,new AccountItem(4545,"40817810000000000002",1200000L));
        accntTable.put(++accntTableID,new AccountItem(4545,"40817810000000000003",1300000L));
        accntTable.put(++accntTableID,new AccountItem(4545,"40817810000000000004",1400000L));
        accntTable.put(++accntTableID,new AccountItem(5656,"40817810000000000005",1500000L));
        accntTable.put(++accntTableID,new AccountItem(5656,"40817810000000000006",1600000L));
        accntTable.put(++accntTableID,new AccountItem(5656,"40817810000000000007",1700000L));
        accntTable.put(++accntTableID,new AccountItem(5656,"40817810000000000008",1800000L));
    }

    public static Optional<ArrayList<String>> giveClientAccounts(Integer clientID) {
        return Optional.ofNullable(accntTable.values().stream()
                .filter(x -> x.getClientID().equals(clientID))
                .map(AccountItem::getNumber)
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    public static Optional<Long> giveAccountBalance(String number) {
        return accntTable.values().stream()
                .filter(x -> x.getNumber().equals(number))
                .map(AccountItem::getBalance)
                .findFirst();//.orElse(0L);
    }


//    public Long giveAccountBalance(String number) {
//        return accntTable.values().stream()
//                .filter(x -> x.getNumber().equals(number))
//                .map(Account::getBalance)
//                .findFirst().orElse(0L);
//    }

}
