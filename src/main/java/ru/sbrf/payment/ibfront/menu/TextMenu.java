package ru.sbrf.payment.ibfront.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TextMenu {
    private final ArrayList<MenuItem> menuItems;

    public TextMenu(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void useTextMenu(String title) throws MenuCancelExeption, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            writeMenu(title);
            int pos;
            try {
                pos = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("Error: Введен некорректный номер пункта. <Press Enter>");
                reader.readLine();
                continue;
            }
            if (pos == menuItems.size()) {
                for (MenuItem mi:menuItems) if (!mi.isReady()) throw new MenuCancelExeption("Отказ от ввода полного набора реквизитов.");
                else return;
            }
            else if (0 <= pos && pos < menuItems.size()) System.out.print("Введите значение [" + menuItems.get(pos).getHint() + "]: ");
            else {
                System.out.println("Ошибка выбора номера строки.");
                reader.read();
                continue;
            }
            String inp = reader.readLine();
            menuItems.get(pos).getItem().setInputString(inp);
            if (menuItems.get(pos).getItem().check()) {
                menuItems.get(pos).setReady(true);
            } else {
                System.out.println("Error: Введено некорректное значение. <Press Enter>");
                reader.readLine();
            }
        }
    }

    private void writeMenu (String title) {
        System.out.println(title);
        for (int i = 0; i < menuItems.size(); i++) { // не используется foreach, т.к. все равно нужен счетчик
            System.out.print(i + " - " + menuItems.get(i).getHint());
            if (menuItems.get(i).isReady()) System.out.print(" {" + menuItems.get(i).getItem().getInputString() + "}");
            System.out.println();
        }
        System.out.println(menuItems.size() + " - Выход");
        System.out.print("> ");
    }


}
