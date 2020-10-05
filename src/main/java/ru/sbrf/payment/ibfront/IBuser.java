package ru.sbrf.payment.ibfront;

import ru.sbrf.payment.common.exchange.*;
import ru.sbrf.payment.ibfront.inputs.Inputs;
import ru.sbrf.payment.ibfront.inputs.PaymentCurrency;
import ru.sbrf.payment.ibfront.inputs.PaymentSumma;
import ru.sbrf.payment.ibfront.inputs.PhoneNumber;
import ru.sbrf.payment.ibfront.menu.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class IBuser {

    public static void main(String[] args) throws IOException {
        IBuser sbClient = new IBuser();
        sbClient.userInterface();
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

        // формируем контейнер с запросом.
        Container clNumCont = new ClientAuthenticContainer(clientNumber, passWord);

// обращение к серверу для аутентификации и получения списка счетов клиента.
        IBclient ibclient = new IBclient();
        ServerAccntContainer accntCont = null;
        try {
            accntCont = ibclient.giveFromTheServer(clNumCont, ServerAccntContainer.class);
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
        Container payCont = new ClientPaymentContainer( clientNumber+"",
                                                  accnt,
                                                  (Long) mnu.get(1).getItem().conversion(),
                                                  (Integer) mnu.get(2).getItem().conversion(),
                                                  (String) mnu.get(0).getItem().conversion());
        ServerResultContainer resCont = null;

        // Отправка платежа на сервер
        try {
            resCont = ibclient.giveFromTheServer(payCont, ServerResultContainer.class);
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
