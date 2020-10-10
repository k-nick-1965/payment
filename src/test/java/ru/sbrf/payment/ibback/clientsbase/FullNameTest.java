package ru.sbrf.payment.ibback.clientsbase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FullNameTest {
//      FullName(String lastname, String firstname, String middlename)
    FullName fn = new FullName("Хунта", "Кристобаль", "Хозевич");

    @Test
    void getLastname() {
        assertEquals("Хунта", fn.getLastname());
    }

    @Test
    void getFirstname() {
        assertEquals("Кристобаль", fn.getFirstname());
    }

    @Test
    void getMiddlename() {
        assertEquals("Хозевич", fn.getMiddlename());
    }

}