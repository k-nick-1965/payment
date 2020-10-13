package exchange;

import java.io.Serializable;
import java.util.Date;

public class Pack implements Serializable {
    private String clientNumber;
    private Date timeStamp;

    public Pack(String clientNumber) {
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

    public Pack() {
        this.clientNumber = "0";
        this.timeStamp = new Date();
    }


}
