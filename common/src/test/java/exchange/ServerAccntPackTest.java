package exchange;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerAccntPackTest {
//    public ServerAccntContainer(String clientNumber, ArrayList<String> clientAccounts) {
    private ServerAccntPack sacAccnt = new ServerAccntPack("5555", new ArrayList<String>(Arrays.asList("40817810000000000000", "40817810000000000001")));
    private ServerAccntPack sacError = new ServerAccntPack("5555", ExchangeResult.UNKNOWN_ERROR, "Ошибка без расшифровки");

    @Test
    void getCode() {
        assertEquals(ExchangeResult.OK, sacAccnt.getCode());
        assertEquals(ExchangeResult.UNKNOWN_ERROR, sacError.getCode());
    }

    @Test
    void getHint() {
        assertEquals("", sacAccnt.getHint());
        assertEquals("Ошибка без расшифровки", sacError.getHint());
    }

    @Test
    void getClientAccounts() {
        assertEquals(new ArrayList<String>(Arrays.asList("40817810000000000000", "40817810000000000001")), sacAccnt.getClientAccounts());
        assertEquals(new ArrayList<String>(0), sacError.getClientAccounts());
    }
}