package ru.sbrf.payment.common.exchange;

import java.io.Serializable;
import java.util.Date;

public class Container implements Serializable {
    private String clientNumber;
    private Date timeStamp;

    public Container(String clientNumber) {
        this.clientNumber = clientNumber;
        this.timeStamp = new Date();
    }

    public Long viewUniqueNumber() {
        return (((long) clientNumber.hashCode() * Integer.MAX_VALUE) + timeStamp.hashCode());
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

// все, что дальше - нужно только для сериализации/десериализации

    public Container() {
        this.clientNumber = "0";
        this.timeStamp = new Date();
    }


}
