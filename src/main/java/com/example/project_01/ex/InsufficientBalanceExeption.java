package com.example.project_01.ex;

public class InsufficientBalanceExeption extends Throwable{
    public InsufficientBalanceExeption(String message){
        super(message);
    }
    public InsufficientBalanceExeption(String message, Throwable cause){
        super(message,cause);
    }
}
