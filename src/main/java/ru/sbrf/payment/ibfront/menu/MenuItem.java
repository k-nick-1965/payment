package ru.sbrf.payment.ibfront.menu;

import lombok.Getter;
import lombok.Setter;
import ru.sbrf.payment.ibfront.inputs.Inputs;

@Getter
@Setter
public class MenuItem<T  extends Inputs> {
    private String hint;
    private T item;
    private boolean ready;

    public MenuItem(String hint, T item) {
        this.hint = hint;
        this.item = item;
        this.ready = false;
    }

    public MenuItem(String hint, T item, boolean ready) {
        this.hint = hint;
        this.item = item;
        this.ready = ready;
    }

}





