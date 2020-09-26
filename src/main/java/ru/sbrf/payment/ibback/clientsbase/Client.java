package ru.sbrf.payment.ibback.clientsbase;

import lombok.Getter;

@Getter
public class Client {
    private String clientNumber;
    private Integer passWordHash;
    private FullName fullName;

    public Client(String clientNumber, String passWord, FullName fullName) {
        this.clientNumber = clientNumber;
        this.passWordHash = passWord.hashCode();
        this.fullName = fullName;
    }

}

