package ibback.clientsbase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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