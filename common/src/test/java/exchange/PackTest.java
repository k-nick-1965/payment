package exchange;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class PackTest {

    @Test
    void viewUniqueNumber() {
        Pack cnt = new Pack("5555");
        Long calcUN = (((long) cnt.getClientNumber().hashCode() * Integer.MAX_VALUE) + cnt.getTimeStamp().hashCode());
        assertEquals(calcUN,cnt.viewUniqueNumber());
    }

    @Test
    void getClientNumber() {
        Pack cnt = new Pack("5555");
        assertEquals("5555",cnt.getClientNumber());
    }

    @Test
    void getTimeStamp() {
        Date tsb = new Date();
        Pack cnt = new Pack("5555");
        Date tsa = new Date();
        if ((tsb.compareTo(cnt.getTimeStamp())>0) || (tsa.compareTo(cnt.getTimeStamp())<0)) {fail("Error in Container.getTimeStamp()");}
   }
}