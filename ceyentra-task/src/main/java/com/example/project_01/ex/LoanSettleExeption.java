package com.example.project_01.ex;

public class LoanSettleExeption extends Throwable{
    public LoanSettleExeption(String message){
        super(message);
    }
    public LoanSettleExeption(String message, Throwable cause){
        super(message,cause);
    }
}
