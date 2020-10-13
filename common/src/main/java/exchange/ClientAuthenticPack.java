package exchange;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ClientAuthenticPack extends Pack implements Serializable {
    private String passWord;

    public ClientAuthenticPack(String clientNumber) {
        super(clientNumber);
    }

    public ClientAuthenticPack(String clientNumber, String passWord) {
        super(clientNumber);
        this.passWord = passWord;
    }

    // все, что дальше - нужно только для сериализации/десериализации
    public ClientAuthenticPack() {
        this.passWord = "";
    }

}
