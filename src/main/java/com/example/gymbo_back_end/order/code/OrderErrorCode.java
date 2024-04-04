package com.example.gymbo_back_end.order.code;

import com.example.gymbo_back_end.core.commom.code.BodyCode;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum OrderErrorCode implements BodyCode {
    ORDER_NOT_FOUND("ORDER01","존재하지 않는 주문입니다.");


    private final String message;
    private final String code;

    OrderErrorCode(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
