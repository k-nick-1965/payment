package ru.sbrf.payment.ibfront.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class TextMenu {
    private ArrayList<MenuItem> menuItems;

    public TextMenu(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void useTextMenu(String title) throws MenuBadNumberException, MenuCancelExeption, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            writeMenu(title);
            int pos = 0;
            try {
                pos = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                System.out.println("Error: Введен некорректный номер пункта. <Press any key>");
                reader.read();
                continue;
            }
            if (pos == menuItems.size()) {
                for (MenuItem mi:menuItems) if (!mi.ready) throw new MenuCancelExeption();
                else return;
            }
            else if (0 <= pos && pos < menuItems.size()) System.out.print("Введите значение [" + menuItems.get(pos).hint + "]: ");
            else throw new MenuBadNumberException();
            String inp = reader.readLine();
            if (Pattern.matches(menuItems.get(pos).mask,inp)) {
                menuItems.get(pos).input = inp;
                menuItems.get(pos).ready = true;
            } else {
                System.out.println("Error: Введено некорректное значение. <Press any key>");
                reader.read();
            }
        }
    }

    private void writeMenu (String title) {
        System.out.println(title);
        for (int i = 0; i < menuItems.size(); i++) { // не используется foreach, т.к. все равно нужен счетчик
            System.out.print(i + " - " + menuItems.get(i).hint);
            if (menuItems.get(i).ready) System.out.print(" (" + menuItems.get(i).input + ")");
            System.out.println("");
        }
        System.out.println(menuItems.size() + " - Выход");
        System.out.print("> ");
    }


}
