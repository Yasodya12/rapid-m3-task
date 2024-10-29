package com.example.project_01.ex;

public class LoanExeption extends Throwable {
    public LoanExeption(String message){
        super(message);
    }
    public LoanExeption(String message, Throwable cause){
        super(message,cause);
    }
}
