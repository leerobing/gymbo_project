package com.example.gymbo_back_end.core.commom.exception.auth;

public class InvalidPasswordException extends IllegalArgumentException {

    private Integer status;
    public InvalidPasswordException() {}

    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException(String message,Integer status) {
        super(message);
        this.status = status;
    }
}
