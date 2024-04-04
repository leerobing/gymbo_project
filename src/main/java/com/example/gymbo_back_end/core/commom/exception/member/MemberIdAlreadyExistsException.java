package com.example.gymbo_back_end.core.commom.exception.member;

public class MemberIdAlreadyExistsException extends RuntimeException{

    Integer status;

    public MemberIdAlreadyExistsException() {}

    public MemberIdAlreadyExistsException(String message) {
        super(message);
    }
    public MemberIdAlreadyExistsException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
