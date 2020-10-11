package ru.sbrf.payment.ibback.clientsbase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientItemsTest {
    private ClientItem ci = new ClientItem("0000", "0001", new FullName("Хунта", "Кристобаль", "Хозевич"));

    @Test
    void giveClient_INTEGER() {
        assertEquals(ci.toString(),ClientItems.giveClient(3).get().toString());
    }

    @Test
    void giveClient_STRING() {
        assertEquals(ci.toString(),ClientItems.giveClient("0000").get().toString());
    }

    @Test
    void giveClientID() {
        assertEquals(3,ClientItems.giveClientID("0000").get());
    }

    @Test
    void authentication() {
        assertEquals(3,ClientItems.authentication("0000", "0001").get());
    }
}