package com.example.project_01.ex;

public class AccountException extends Throwable{
    public AccountException(String message){
        super(message);
    }
    public AccountException(String message, Throwable cause){
        super(message,cause);
    }
}
