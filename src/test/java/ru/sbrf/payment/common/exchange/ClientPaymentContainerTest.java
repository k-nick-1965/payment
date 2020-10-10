package ru.sbrf.payment.common.exchange;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientPaymentContainerTest {
//    public ClientPaymentContainer(String clientNumber, String account, Long summa, Integer currency, String mobileNumber) {
    private ClientPaymentContainer cpc = new ClientPaymentContainer("5555", "40817810000000000000", 999L, 810, "0123456789");

    @Test
    void getAccount() {
        assertEquals("40817810000000000000", cpc.getAccount());
    }

    @Test
    void getSumma() {
        assertEquals(999L, cpc.getSumma());
    }

    @Test
    void getCurrency() {
        assertEquals(810, cpc.getCurrency());
    }

    @Test
    void getMobileNumber() {
        assertEquals("0123456789", cpc.getMobileNumber());
    }

}