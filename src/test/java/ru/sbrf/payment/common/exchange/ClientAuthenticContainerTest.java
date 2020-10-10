package ru.sbrf.payment.common.exchange;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientAuthenticContainerTest {

    @Test
    void getPassWord() {
        ClientAuthenticContainer cac = new ClientAuthenticContainer("5555", "PassWord");
        assertEquals("PassWord", cac.getPassWord());
    }
}