package ru.sbrf.payment.common.exchange;

public class WaitAnswerExeption extends Exception{
    public WaitAnswerExeption(){}
    public WaitAnswerExeption(String message) {
        super(message);
    }
}
