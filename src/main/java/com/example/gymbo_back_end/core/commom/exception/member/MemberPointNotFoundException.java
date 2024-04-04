package com.example.gymbo_back_end.core.commom.exception.member;

import javax.persistence.EntityNotFoundException;

public class MemberPointNotFoundException extends EntityNotFoundException {

    Integer status;
    public MemberPointNotFoundException() {
    }

    public MemberPointNotFoundException(String message, Integer status) {
        super(message);
        this.status = status;
    }

    public MemberPointNotFoundException(String message) {
        super(message);
    }
}
