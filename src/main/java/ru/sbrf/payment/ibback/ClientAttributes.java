package ru.sbrf.payment.ibback;

public interface ClientAttributes {
    String[] getClientAccounts(String clientNumber);  // получить от сервера перечень счетов клиента
}
