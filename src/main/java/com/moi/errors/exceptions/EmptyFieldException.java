package com.moi.errors.exceptions;

public class EmptyFieldException extends Exception {

    public EmptyFieldException(){};

    public EmptyFieldException(String message){
        super(message);
    }
}
