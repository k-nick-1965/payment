package exchange;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ClientPaymentPack extends Pack implements Serializable {
    private String account;
    private Long summa;
    private Integer currency;
    private String mobileNumber;

    public ClientPaymentPack(String clientNumber, String account, Long summa, Integer currency, String mobileNumber) {
        super(clientNumber);
        this.account = account;
        this.summa = summa;
        this.currency = currency;
        this.mobileNumber = mobileNumber;
    }

    // все, что дальше - нужно только для сериализации/десериализации
    public ClientPaymentPack() {
        this.account = "";
        this.summa = 0L;
        this.currency = 0;
        this.mobileNumber ="";
    }

}
