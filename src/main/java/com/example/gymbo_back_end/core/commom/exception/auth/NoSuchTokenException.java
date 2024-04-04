package com.example.gymbo_back_end.core.commom.exception.auth;

import java.util.NoSuchElementException;

public class NoSuchTokenException extends NoSuchElementException {
    public NoSuchTokenException(String message) {
        super(message);
    }
}
