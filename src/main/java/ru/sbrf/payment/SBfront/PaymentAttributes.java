package ru.sbrf.payment.SBfront;

public interface PaymentAttributes {
    String getClientNumber();   // получить от пользователя его уникальный номер
    String getMobileNumber();   // получить от пользователя для перевода платежа
    double getSumma();          // получить от пользователя сумму платежа
    int getCurrency();          // получить от пользователя код валюты платежа
    String getAccount();        // получить от пользователя дебетуемый счет
}
