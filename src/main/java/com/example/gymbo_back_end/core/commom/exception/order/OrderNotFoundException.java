package com.example.gymbo_back_end.core.commom.exception.order;

import javax.persistence.EntityNotFoundException;

public class OrderNotFoundException extends EntityNotFoundException {

    Integer status;

    public OrderNotFoundException(){}

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Integer status) {
        super(message);
        this.status = status;
    }

}
