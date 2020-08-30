package ru.sbrf.payment.SBback;

public interface ClientAttributes {
    String[] getClientAccounts(String clientNumber);  // получить от сервера перечень счетов клиента
}
