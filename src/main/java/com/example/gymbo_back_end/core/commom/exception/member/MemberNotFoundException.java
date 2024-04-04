package com.example.gymbo_back_end.core.commom.exception.member;

import javax.persistence.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {

    Integer status;

    public MemberNotFoundException(){}

    public MemberNotFoundException(String message) {
        super(message);
    }

    public MemberNotFoundException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
