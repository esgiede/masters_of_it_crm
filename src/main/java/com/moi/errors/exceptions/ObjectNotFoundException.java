package com.moi.errors.exceptions;

public class ObjectNotFoundException extends Exception {
    public ObjectNotFoundException(){}

    public ObjectNotFoundException(String message){
        super(message);
    }
}
