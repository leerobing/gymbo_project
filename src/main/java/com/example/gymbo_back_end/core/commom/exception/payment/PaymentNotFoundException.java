package com.example.gymbo_back_end.core.commom.exception.payment;

import javax.persistence.EntityNotFoundException;

public class PaymentNotFoundException extends EntityNotFoundException {

    Integer status;

    public PaymentNotFoundException(String message) {
        super(message);
    }
    public PaymentNotFoundException(Integer status) {
        this.status = status;
    }

    public PaymentNotFoundException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
