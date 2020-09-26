package ru.sbrf.payment.ibback.clientsbase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Clients {
    // Это должна быть таблица с полями ID, ClientNumber, PassWordHash, Lastname, Firstname, Middlename;
    private static final Map<Integer, Client> clientTable = new HashMap<>();
    private static Integer clientTableID = 0;

    static {  // Помню, что это нехорошо, но, т.к. это эмуляция БД, которую не требуется заполнять, то пойдет.
        clientTable.put(++clientTableID, new Client("4545", "4545", new FullName("Петров", "Петр", "Петрович")));
        clientTable.put(++clientTableID, new Client("5656", "5656", new FullName("Иванов", "Иван", "Иванович")));
        clientTable.put(++clientTableID, new Client("0000", "0000", new FullName("Хунта", "Кристобаль", "Хозевич")));
    }

    public static Optional<Client> giveClient(String clientNumber) {
        return clientTable.values().stream()
                .filter(x -> x.getClientNumber().equals(clientNumber))
                .findFirst();
    }

    public static Optional<Integer> giveClientID(String clientNumber) {
        return clientTable.entrySet().stream()
                .filter(x -> x.getValue().getClientNumber().equals(clientNumber))
                .map(Map.Entry::getKey)
                .findFirst();
    }

    public static boolean authentication (String clientNumber, String passWord) {
        Optional<Client> opt;
        if (!(opt=giveClient(clientNumber)).isPresent()) return false;
        else return (opt.get().getPassWordHash().equals(passWord.hashCode()));
    }

}
