package ibback.clientsbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountItems {
    // Это должна быть таблица с полями ID, userID, AccountNumber, Balance
    // Ща буду реализовывать это на коллекциях.
    // Здесь используются стримы, чтобы потренироваться в их применении.
    private static final Map<Integer, AccountItem> accntTable= new HashMap<>();
    private static Integer accntTableID = 0;

    static {  // Помню, что это нехорошо, но, т.к. это эмуляция БД, которую не требуется заполнять, то пойдет.
        accntTable.put(++accntTableID,new AccountItem(ClientItems.giveClientID("4545").orElse(0),"40817810000000000000",1000000L));
        accntTable.put(++accntTableID,new AccountItem(ClientItems.giveClientID("4545").orElse(0),"40817810000000000001",1100000L));
        accntTable.put(++accntTableID,new AccountItem(ClientItems.giveClientID("4545").orElse(0),"40817810000000000002",1200000L));
        accntTable.put(++accntTableID,new AccountItem(ClientItems.giveClientID("4545").orElse(0),"40817810000000000003",1300000L));
        accntTable.put(++accntTableID,new AccountItem(ClientItems.giveClientID("4545").orElse(0),"40817810000000000004",1400000L));
        accntTable.put(++accntTableID,new AccountItem(ClientItems.giveClientID("5656").orElse(0),"40817810000000000005",1500000L));
        accntTable.put(++accntTableID,new AccountItem(ClientItems.giveClientID("5656").orElse(0),"40817810000000000006",1600000L));
        accntTable.put(++accntTableID,new AccountItem(ClientItems.giveClientID("5656").orElse(0),"40817810000000000007",1700000L));
        accntTable.put(++accntTableID,new AccountItem(ClientItems.giveClientID("5656").orElse(0),"40817810000000000008",1800000L));
    }

    public static Optional<ArrayList<String>> giveClientAccounts(Integer clientID) {
    // счета клиента
        return Optional.ofNullable(accntTable.values().stream()
                .filter(x -> x.getClientID().equals(clientID))
                .map(AccountItem::getNumber)
                .collect(Collectors.toCollection(ArrayList::new)));
    }

    public static Optional<Long> giveAccountBalance(String accnt) {
    // Возвращаем остаток по счету
        return accntTable.values().stream()
                .filter(x -> x.getNumber().equals(accnt))
                .map(AccountItem::getBalance)
                .findFirst();//.orElse(0L);
    }

    public static Optional<AccountItem> giveAccountItem(String accnt) {
        // возвращаем реквизиты счета
        return accntTable.values().stream()
                .filter(x -> x.getNumber().equals(accnt))
                .findFirst();//.orElse(0L);
    }

    public static Optional<AccountItem> giveAccountItem(Integer accntID) {
        // возвращаем реквизиты счета
        if (accntTable.containsKey(accntID)) return Optional.of(accntTable.get(accntID));
        else return Optional.empty();
    }

}
