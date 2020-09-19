package ru.sbrf.payment.ibfront.menu;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuItem {
    private String hint;
    private String mask;
    private String input;
    private boolean ready;

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
