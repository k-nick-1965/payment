package ibfront;

import com.fasterxml.jackson.databind.ObjectMapper;

import exchange.*;
import ibback.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static java.lang.Thread.sleep;

@Component
public class IBclient implements ExchWithServer {
    private String addrHost;
    private String addrIP;
    private Integer port;
    private String protocol;
    private String exchangeDir;
    private IBserver ibserver; // for debuggin only

    public IBclient() throws IOException {
        File file = new File("PayProperties.ini");
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        this.addrHost = properties.getProperty("addrHost");
        this.addrIP = properties.getProperty("addrIP");
        this.port = Integer.parseInt(properties.getProperty("port"));
        this.protocol = properties.getProperty("protocol");
        this.exchangeDir = properties.getProperty("exchangeDir");
        pay();
        ibserver = new IBserver(); // for debuggin
    }

    public void pay() {
        // TODO Здесь должна быть инициализация канала связи между клиентской и серверной частью.
        // TODO при обмене данными через обший каталог в нет нет необходимости.
    }

    @Override
    public <T extends Pack> T giveFromTheServer(@NotNull Pack cont, Class<T> valueType ) throws IOException, WaitAnswerExeption, ClassNotFoundException {
        // отправляем данные на сервер
        // "Class<T> valueType" - написал чисто как попугай из исходников Jackson-а. Что это означает понимаю слабо
        // заглянул в исходники и почувствовал себя ущербным.
        sendToServer(cont);
        ibserver.testingIBserver(); // for debuggin
        // ждем ответ от сервера (дождаться появления файла *.ToClnt, считать содержимое и грохнуть
        String ansName = exchangeDir+"\\"+valueType.getSimpleName()+".ToClnt"; //getName();
        File ansFile = new File(ansName);
        for (int i=0; i<60; i++) {
            // ждем ответ 60 секунд
            if (ansFile.exists()) {
                ObjectMapper mapper = new ObjectMapper();
                // TODO в будущем придется разделить прием данных и десериализацию. сейчас в этом нет необходимости.
                T result = (T) mapper.readValue(ansFile, valueType);
                ansFile.delete();
                return result;
            }
            try { sleep(1000); } catch (InterruptedException e ) {}
         }
        throw new WaitAnswerExeption("Истек таймаут ожидания ответа от сервера.");
    }

    @Override
    public void sendToServer(@NotNull Pack cont) throws IOException {
        // отправляем данные на сервер
        // TODO в будущем придется разделить сериализацию и отправку данных. сейчас в этом нет необходимости.
        File sndFile = new File(exchangeDir+"\\" + cont.getClass().getSimpleName() + ".ToSrv");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(sndFile, cont);
    }
}
