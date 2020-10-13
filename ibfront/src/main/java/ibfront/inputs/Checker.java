package ibfront.inputs;

@FunctionalInterface
interface Checker<T> {
    boolean checking(T inputDate);
}
