package ru.sbrf.payment.ibfront.menu;

public class MenuItem {
    public String hint;
    public String mask;
    public String input;
    public boolean ready;

    public MenuItem(String hint) {
        this.hint = hint;
        this.mask = ".{0,}";
        this.input = "";
        this.ready = false;
    }

    public MenuItem(String hint, String mask) {
        this.hint = hint;
        this.mask = mask;
        this.input = "";
        this.ready = false;
    }

    public MenuItem(String hint, String mask, String input) {
        this.hint = hint;
        this.mask = mask;
        this.input = input;
        this.ready = false;
    }

    public MenuItem(String hint, String mask, String input, boolean ready) {
        this.hint = hint;
        this.mask = mask;
        this.input = input;
        this.ready = ready;
    }


}
