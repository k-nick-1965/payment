package ibback.clientsbase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientItemTest {
//     public ClientItem(String clientNumber, String passWord, FullName fullName) {
    private ClientItem ci = new ClientItem("5555", "0000", new FullName("Хунта", "Кристобаль", "Хозевич"));

    @Test
    void getClientNumber() {
        assertEquals("5555",ci.getClientNumber());
    }

    @Test
    void getPassWordHash() {
        assertEquals("0000",ci.getPassWordHash());
    }

    @Test
    void getFullName() {
        FullName fn = new FullName("Хунта", "Кристобаль", "Хозевич");
        assertEquals(fn,ci.getFullName());
    }
}