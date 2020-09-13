package ru.sbrf.payment.ibfront;

import ru.sbrf.payment.common.exchange.*;
import ru.sbrf.payment.ibfront.menu.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class IBuser {

    public static void main(String[] args) throws IOException, WaitAnserExeption, ClassNotFoundException {
        IBuser sbClient = new IBuser();
        sbClient.userInterface();

    }


    public void userInterface() throws IOException, WaitAnserExeption, ClassNotFoundException {
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

// обращение к серверу для получения списка счетов.
        IBclient ibclient = new IBclient();
        ServerAccntContainer accntCont = ibclient.GetFromTheServer(clNumCont, ServerAccntContainer.class);
        if (accntCont.getCode()!=0) {
            System.out.println(accntCont.getHint());
            System.out.println("До свидания. Ждем Вас снова.");
            return;
        }
        PositionalMenu amnu = new PositionalMenu(accntCont.getClientAccounts());
//

//        Для отладки без сервера - просто перечень счетов
//        ArrayList<String> accnts = new ArrayList<String>();
//        accnts.add("40702810055000000001");;
//        accnts.add("40702810055000000002");;
//        accnts.add("40702810055000000003");;
//        accnts.add("40702810055000000004");;
//        accnts.add("40702810055000000005");;
//        PositionalMenu amnu = new PositionalMenu(accnts);
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

// обращение к серверу для отправки платежа.
        Container payCont = new ClientPaymentContainer( clientNumber+"",
                                                  accnt,
                                                  Integer.parseInt(mnu.get(1).input),
                                                  Integer.parseInt(mnu.get(2).input),
                                                  mnu.get(0).input);
        ServerResultContainer resCont = ibclient.GetFromTheServer(payCont, ServerResultContainer.class);
        System.out.println(resCont.getHint());

    }


}
