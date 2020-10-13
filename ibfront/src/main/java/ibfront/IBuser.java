package ibfront;

import exchange.*;
import ibfront.inputs.*;
import ibfront.*;
import ibfront.menu.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Component
public class IBuser {
    static ApplicationContext context;

    public static void main(String[] args) throws IOException {
        context = new AnnotationConfigApplicationContext(IBuser.class,IBclient.class);
        IBuser sbClient = context.getBean(IBuser.class);
        sbClient.userInterface();

//        IBuser sbClient = new IBuser();
//        sbClient.userInterface();
    }

    public void userInterface() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String clientNumber = "";
        String passWord = "";

        // Ввод идентификатора и (логина) и пароля клиента
        System.out.print("Введите идентификатор клиента: ");
        clientNumber = reader.readLine();
        if (clientNumber.length() < 4) {
            System.out.println("До свидания. Ждем Вас снова.");
            return;
        }
        System.out.print("Введите пароль: ");
        passWord = reader.readLine();
        if (passWord.length() < 4) {
            System.out.println("До свидания. Ждем Вас снова.");
            return;
        }

        // формируем пакет с запросом.
        Pack clNumCont = new ClientAuthenticPack(clientNumber, passWord);

// обращение к серверу для аутентификации и получения списка счетов клиента.
        IBclient ibclient = context.getBean(IBclient.class);
//        IBclient ibclient = new IBclient();
        ServerAccntPack accntCont = null;
        try {
            accntCont = ibclient.giveFromTheServer(clNumCont, ServerAccntPack.class);
        } catch (WaitAnswerExeption e) {
            System.out.println(e.getMessage());
            System.out.println("До свидания. Ждем Вас снова.");
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("Внутреняя ошибка приложения (не реализована услуга).");
            System.out.println("До свидания. Ждем Вас снова.");
            return;
        }
        if (accntCont.getCode()!=ExchangeResult.OK) {
            // проверка на наличие ошибок на стороне сервера
            System.out.println(accntCont.getHint());
            System.out.println("До свидания. Ждем Вас снова.");
            return;
        }

        // Выбор счета списания
        PositionalMenu amnu = new PositionalMenu(accntCont.getClientAccounts());
        String accnt="";
        try {
            accnt = amnu.usePositionalMenu("Выберите счет списания:");
        } catch (MenuCancelExeption e) {
            System.out.println(e.getMessage());
            System.out.println("До свидания. Ждем Вас снова.");
            return;
        }

        // Ввод реквизитов платежа.
        ArrayList<MenuItem> mnu = new ArrayList<>();
        mnu.add(new MenuItem<PhoneNumber>("Номер телефона", new PhoneNumber("")));
        mnu.add(new MenuItem<PaymentSumma>("Сумма", new PaymentSumma("")));
        mnu.add(new MenuItem<PaymentCurrency>("Код валюты", new PaymentCurrency("810"), true ));
        TextMenu pmnu = new TextMenu(mnu);
        try {
            pmnu.useTextMenu("Введите параметры платежа");
        } catch (MenuCancelExeption e) {
            System.out.println(e.getMessage());
            System.out.println("До свидания. Ждем Вас снова.");
            return;
        }

        // Формирование пакета с платежом
        Pack payCont = new ClientPaymentPack( clientNumber+"",
                                                  accnt,
                                                  (Long) mnu.get(1).getItem().conversion(),
                                                  (Integer) mnu.get(2).getItem().conversion(),
                                                  (String) mnu.get(0).getItem().conversion());
        ServerResultPack resCont = null;

        // Отправка платежа на сервер
        try {
            resCont = ibclient.giveFromTheServer(payCont, ServerResultPack.class);
        } catch (WaitAnswerExeption e) {
            System.out.println(e.getMessage());
            System.out.println("До свидания. Ждем Вас снова.");
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("Внутреняя ошибка приложения (не реализована услуга).");
            System.out.println("До свидания. Ждем Вас снова.");
            return;
        }

        // Ответ сервера. здесь уже без разницы - ошибка или нет.
        System.out.println(resCont.getHint());
    }
}
