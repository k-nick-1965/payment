package ru.sbrf.payment.ibfront.menu;

public class MenuCancelExeption extends Exception{
    public MenuCancelExeption() { }
    public MenuCancelExeption(String message) {
        super(message);
    }
}
