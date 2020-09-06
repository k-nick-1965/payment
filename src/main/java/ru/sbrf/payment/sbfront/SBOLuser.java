package ru.sbrf.payment.sbfront;

import ru.sbrf.payment.exchange.*;
import ru.sbrf.payment.menu.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SBOLuser {

    public static void main(String[] args) throws IOException {
        SBOLuser sbClient = new SBOLuser();
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

// обращение к серверу для получения списка счетов.
        SBOL sbol = new SBOL();
        Container clNumCont = new AuthenticContainer(clientNumber+"");
        sbol.SendToServer(clNumCont);
        AccntContainer accntCont = (AccntContainer) sbol.GetFromTheServer(clNumCont);
//        PositionalMenu amnu = new PositionalMenu(accntCont.getClientAccounts());

// TODO: Пока не реализовано серверу  - просто перечень счетов
        ArrayList<String> accnts = new ArrayList<String>();
        accnts.add("40702810055000000001");;
        accnts.add("40702810055000000002");;
        accnts.add("40702810055000000003");;
        accnts.add("40702810055000000004");;
        accnts.add("40702810055000000005");;
        PositionalMenu amnu = new PositionalMenu(accnts);
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
        mnu.add(new MenuItem("Код валюты", "\\d{3}","по умолчанию - 810", true ));
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
        Container payCont = new PaymentContainer( clientNumber+"",
                                                  accnt,
                                                  Integer.parseInt(mnu.get(1).input),
                                                  Integer.parseInt(mnu.get(2).input),
                                                  mnu.get(0).input);
        ResultContainer resCont = (ResultContainer) sbol.GetFromTheServer(payCont);
        System.out.println(resCont.getHint());

    }










// TODO: Дальше все лишнее - тгжно переделать
//    private String clientNumber;
//    private String mobileNumber;
//    private double summa;
//    private int currency;
//    private String account;
//
//    public SBOLuser() {
//    }
//
//    @Override
//    public String getClientNumber() {
//        return clientNumber;
//    }
//
//    @Override
//    public String getMobileNumber() {
//        return this.mobileNumber;
//    }
//
//    @Override
//    public double getSumma() {
//        return this.summa;
//    }
//
//    @Override
//    public int getCurrency() {
//        return this.currency;
//    }
//
//    @Override
//    public String getAccount() {
//        return this.account;
//    }
}
