package com.moi.errors.exceptions;

public class ObjectAlreadyExistException extends Exception {
    public ObjectAlreadyExistException() {
    }

    public ObjectAlreadyExistException(String message) {
        super(message);
    }
}
