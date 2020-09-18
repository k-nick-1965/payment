package ru.sbrf.payment.common.exchange;

public enum ExchangeResult {
    OK,
    MISSING_USER_NUMBER, // ("Ошибка. Отсутствующий номер клиента."),
    CONTAINER_OPENING_ERROR, // ("Ошибка открытия пакета."),
    CONTAINER_DUPLICATE_ERROR; // ("Ошибка. Повтор пакета.");

}

//public enum ExchangeErrors {
//    OK (""),
//    MISSING_USER_NUMBER ("Ошибка. Отсутствующий номер клиента."),
//    CONTAINER_OPENIN_ERROR ("Ошибка открытия пакета."),
//    CONTAINER_DUPLICATE_ERROR ("Ошибка. Повтор пакета.");
//
//    private String hint;
//
//    ExchangeErrors(String s) {
//        this.hint=s;
//    }
//}
