package ru.sbrf.payment.ibfront;

public class WaitAnswerExeption extends Exception{
    public WaitAnswerExeption(){}
    public WaitAnswerExeption(String message) {
        super(message);
    }
}
