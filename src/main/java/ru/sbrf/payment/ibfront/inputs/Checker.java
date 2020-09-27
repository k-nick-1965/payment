package ru.sbrf.payment.ibfront.inputs;

@FunctionalInterface
interface Checker<T> {
    boolean checking(T inputDate);
}
