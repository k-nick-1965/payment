package ru.sbrf.payment.ibback.clientsbase;

import lombok.Getter;

@Getter
public class AccountItem {
    private Integer clientID = null;
    private String number = null;
    private Long balance = null;

    public AccountItem(Integer clientID, String number, Long balance) {
        this.clientID = clientID;
        this.number = number;
        this.balance = balance;
    }

}
