package ru.sbrf.payment.ibfront;

import ru.sbrf.payment.common.exchange.*;
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
        Integer clientNumber = 0;
        while (true) {
            System.out.print("Введите номер клиента: ");
            try {
                clientNumber = Integer.parseInt(reader.readLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: Введен некорректный номер клиента. <Press any key>");
                reader.read();
            }
        }
        Container clNumCont = new ClientAuthenticContainer(clientNumber+"");

// обращение к серверу для аутентификации и получения списка счетов клиента.
        IBclient ibclient = new IBclient();
        ServerAccntContainer accntCont = null;
        try {
            accntCont = ibclient.GetFromTheServer(clNumCont, ServerAccntContainer.class);
        } catch (WaitAnswerExeption waitAnswerExeption) {
            System.out.println("Нет связи с сервером.");
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
            System.out.println("До свидания. Ждем Вас снова.");
            return;
        } catch (MenuBadNumberException e) { // Exeption сделан только в тренировочных целях. По-хорошему - нужно вернуться к выбору.
            System.out.println("Ошибка выбора номера строки.");
            System.out.println("До свидания. Ждем Вас снова.");
            return;
        }
// Ввод реквизитов платежа.
        ArrayList<MenuItem> mnu = new ArrayList<MenuItem>();
        mnu.add(new MenuItem("Номер телефона (10 цифр)", "\\d{10}"));
        mnu.add(new MenuItem("Сумма (целое число)", "\\d{1,}"));
        mnu.add(new MenuItem("Код валюты", "\\d{3}","810", true ));
        TextMenu pmnu = new TextMenu(mnu);
        try {
            pmnu.useTextMenu("Введите параметры платежа");
        } catch (MenuCancelExeption e) {
            System.out.println("До свидания. Ждем Вас снова.");
            return;
        } catch (MenuBadNumberException e) { // Exeption сделан только в тренировочных целях. По-хорошему - нужно вернуться к выбору.
            System.out.println("Ошибка выбора номера строки.");
            System.out.println("До свидания. Ждем Вас снова.");
            return;
        }
// Формирование пакета с платежом
        Container payCont = new ClientPaymentContainer( clientNumber+"",
                                                  accnt,
                                                  Integer.parseInt(mnu.get(1).input),
                                                  Integer.parseInt(mnu.get(2).input),
                                                  mnu.get(0).input);
        ServerResultContainer resCont = null;
        try {
// Отправка платежа на сервер
            resCont = ibclient.GetFromTheServer(payCont, ServerResultContainer.class);
        } catch (WaitAnswerExeption waitAnswerExeption) {
            System.out.println("Нет связи с сервером.");
            System.out.println("До свидания. Ждем Вас снова.");
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("Внутреняя ошибка приложения (не реализована услуга).");
            System.out.println("До свидания. Ждем Вас снова.");
            return;
        }
        System.out.println(resCont.getHint()); // Ответ сервера. здесь уже без разницы - ошибка или нет.
    }
}
