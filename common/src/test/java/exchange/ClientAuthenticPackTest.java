package exchange;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientAuthenticPackTest {

    @Test
    void getPassWord() {
        ClientAuthenticPack cac = new ClientAuthenticPack("5555", "PassWord");
        assertEquals("PassWord", cac.getPassWord());
    }
}