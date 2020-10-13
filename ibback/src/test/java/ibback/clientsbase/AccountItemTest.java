package ibback.clientsbase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AccountItemTest {
//      public AccountItem(Integer clientID, String number, Long balance) {
    AccountItem ai = new AccountItem(99,"40817810000000000099",1000099L);

    @Test
    void checkCurrency() {
        try {
            assertEquals(ai.toString(),ai.checkCurrency(810).toString());
        } catch (AccountCheckExeption e) {
            fail("checkCurrency: the thrown accountCheckExeption.");
        }
        try {
            ai.checkCurrency(840);
            fail("checkCurrency: not an accountCheckExeption was thrown.");
        } catch (AccountCheckExeption ignored) {
        }
    }

    @Test
    void checkBalance() {
        try {
            assertEquals(ai.toString(),ai.checkBalance(1000099L).toString());
        } catch (AccountCheckExeption e) {
            fail("checkBalance: the thrown accountCheckExeption.");
        }
        try {
            ai.checkBalance(100L);
            fail("checkBalance: not an accountCheckExeption was thrown.");
        } catch (AccountCheckExeption ignored) {
        }
    }

    @Test
    void checkOwner_STRING() {
        // TODO Это ну очень сложно.
    }

    @Test
    void checkOwner_INTEGER() {
        try {
            assertEquals(ai.toString(),ai.checkOwner(99).toString());
        } catch (AccountCheckExeption e) {
            fail("checkOwner_INTEGER: the thrown accountCheckExeption.");
        }
        try {
            ai.checkOwner(66);
            fail("checkOwner_INTEGER: not an accountCheckExeption was thrown.");
        } catch (AccountCheckExeption ignored) {
        }
    }

    @Test
    void getClientID() {
        assertEquals(99, ai.getClientID());
    }

    @Test
    void getNumber() {
        assertEquals("40817810000000000099", ai.getNumber());
    }

    @Test
    void getBalance() {
        assertEquals(1000099L, ai.getBalance());
    }
}