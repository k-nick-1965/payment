package ru.sbrf.payment.ibfront.inputs;

@FunctionalInterface
interface Validator<T> {
    boolean validation(T inputDate);
}
