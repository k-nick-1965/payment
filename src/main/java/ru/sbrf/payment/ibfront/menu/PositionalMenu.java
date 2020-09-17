package ru.sbrf.payment.ibfront.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PositionalMenu {

    private ArrayList<String> menuItems;

    public PositionalMenu(ArrayList<String> menuItems) {
        this.menuItems = menuItems;
    }

    public String usePositionalMenu(String title) throws MenuBadNumberException, MenuCancelExeption {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            writeMenu(title);
            System.out.print("> ");
            int pos = 0;
            try {
                pos = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                System.out.println("Error: Введен некорректный номер пункта.");
                continue;
            }
            if (0 <= pos && pos < menuItems.size()) return menuItems.get(pos);
            else if (pos == menuItems.size()) throw new MenuCancelExeption();
            else throw new MenuBadNumberException("Ошибка выбора номера строки.");
        }
    }

    private void writeMenu (String title) {
        System.out.println(title);
        for (int i = 0; i < menuItems.size(); i++) { // не используется foreach, т.к. все равно нужен счетчик
            System.out.println(i + " - " + menuItems.get(i));
        }
        System.out.println(menuItems.size() + " - Отмена");
    }

}
