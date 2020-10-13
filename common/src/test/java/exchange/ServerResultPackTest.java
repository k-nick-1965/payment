package exchange;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerResultPackTest {
//    public ServerResultContainer(String clientNumber, ExchangeResult code, String hint) {
    ServerResultPack src = new ServerResultPack("5555", ExchangeResult.UNKNOWN_ERROR, "Ошибка без расшифровки");

    @Test
    void getCode() {
        assertEquals(ExchangeResult.UNKNOWN_ERROR, src.getCode());
    }

    @Test
    void getHint() {
        assertEquals("Ошибка без расшифровки", src.getHint());
    }
}