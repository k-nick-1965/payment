package ibfront.menu;

public class MenuCancelExeption extends Exception{
    public MenuCancelExeption() { super("MenuCancelExeption ");}
    public MenuCancelExeption(String message) {
        super(message);
    }
}
