package ru.sbrf.payment.sbback;

public interface ClientAttributes {
    String[] getClientAccounts(String clientNumber);  // получить от сервера перечень счетов клиента
}
