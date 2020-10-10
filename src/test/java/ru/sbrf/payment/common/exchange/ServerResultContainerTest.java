package ru.sbrf.payment.common.exchange;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServerResultContainerTest {
//    public ServerResultContainer(String clientNumber, ExchangeResult code, String hint) {
    ServerResultContainer src = new ServerResultContainer("5555", ExchangeResult.UNKNOWN_ERROR, "Ошибка без расшифровки");

    @Test
    void getCode() {
        assertEquals(ExchangeResult.UNKNOWN_ERROR, src.getCode());
    }

    @Test
    void getHint() {
        assertEquals("Ошибка без расшифровки", src.getHint());
    }
}