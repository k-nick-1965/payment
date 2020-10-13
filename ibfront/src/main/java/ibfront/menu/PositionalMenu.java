package ibfront.menu;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PositionalMenu {

    private final ArrayList<String> menuItems;

    public PositionalMenu(@NotNull ArrayList<String> menuItems) {
        this.menuItems = menuItems;
    }

    public String usePositionalMenu(@NotNull String title) throws MenuCancelExeption, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            writeMenu(title);
            System.out.print("> ");
            int pos = 0;
            try {
                pos = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("Error: Введен некорректный номер пункта. <Press Enter>");
                reader.readLine();
                continue;
            }
            if (0 <= pos && pos < menuItems.size()) return menuItems.get(pos);
            else if (pos == menuItems.size()) throw new MenuCancelExeption("Отказ от выбора.");
            else {
                System.out.println("Ошибка выбора номера строки. <Press Enter>");
                reader.readLine();
                continue;
            }
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
