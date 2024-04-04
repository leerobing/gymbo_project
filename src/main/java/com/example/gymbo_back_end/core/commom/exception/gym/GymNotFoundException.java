package com.example.gymbo_back_end.core.commom.exception.gym;

import javax.persistence.EntityNotFoundException;

public class GymNotFoundException extends EntityNotFoundException {

    private Integer status;

    GymNotFoundException(){}
    public GymNotFoundException(String message) {
        super(message);
    }
    GymNotFoundException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
