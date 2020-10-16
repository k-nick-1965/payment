package ibback.clientsbase;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.security.*;

public class ClientItems {
    // Это должна быть таблица с полями ID, ClientNumber, PassWordHash, Lastname, Firstname, Middlename;
    private static final Map<Integer, ClientItem> clientTable = new HashMap<>();
    private static Integer clientTableID = 0;

    static {  // Помню, что это нехорошо, но, т.к. это эмуляция БД, которую не требуется заполнять, то пойдет.
        clientTable.put(++clientTableID, new ClientItem("4545", "4545", new FullName("Петров", "Петр", "Петрович")));
        clientTable.put(++clientTableID, new ClientItem("5656", "5656", new FullName("Иванов", "Иван", "Иванович")));
        clientTable.put(++clientTableID, new ClientItem("0000", "0001", new FullName("Хунта", "Кристобаль", "Хозевич")));
    }

    public static Optional<ClientItem> giveClient(Integer clientID) {
        if (clientTable.containsKey(clientID)) {return Optional.of(clientTable.get(clientID));}
        else {return Optional.empty();}
    }

    public static Optional<ClientItem> giveClient(String clientNumber) {
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

    public static Optional<Integer> authentication (String clientNumber, String passWord) {
        MessageDigest digester;
        try {
            digester = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return Optional.empty();
        }
        // добавляем соль - clientNumber
        digester.update(clientNumber.getBytes());
        // хэшируем
        byte[] passWordHashByte = digester.digest(passWord.getBytes());
        // преобразуем хэш в строку шестнадцатиричных символов длиной 32 символа
        String passWordHash = "";
        for (int i = 0; i < passWordHashByte.length; i++) {
            String s = Integer.toHexString(0xFF & passWordHashByte[i]);
            passWordHash = passWordHash + ((s.length()==1) ? "0" + s : s );
        }
        // сравниваем полученный и хранящийся хэши
        Optional<ClientItem> opt;
        if (!(opt=giveClient(clientNumber)).isPresent()) return Optional.empty();
        else if (!opt.get().getPassWordHash().equals(passWordHash)) return Optional.empty();
        else return giveClientID(clientNumber);
    }

}
