package ru.sbrf.payment.common.exchange;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    @Test
    void viewUniqueNumber() {
        Container cnt = new Container("5555");
        Long calcUN = (((long) cnt.getClientNumber().hashCode() * Integer.MAX_VALUE) + cnt.getTimeStamp().hashCode());
        assertEquals(calcUN,cnt.viewUniqueNumber());
    }

    @Test
    void getClientNumber() {
        Container cnt = new Container("5555");
        assertEquals("5555",cnt.getClientNumber());
    }

    @Test
    void getTimeStamp() {
        Date tsb = new Date();
        Container cnt = new Container("5555");
        Date tsa = new Date();
        if ((tsb.compareTo(cnt.getTimeStamp())>0) || (tsa.compareTo(cnt.getTimeStamp())<0)) {fail("Error in Container.getTimeStamp()");}
   }
}