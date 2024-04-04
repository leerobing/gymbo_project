package com.example.gymbo_back_end.payment.code;

import com.example.gymbo_back_end.core.commom.code.BodyCode;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum PaymentErrorCode implements BodyCode {
    PAYMENT_NOT_FOUND("PAY01", "결제정보를 찾을 수 없습니다."),
    ;

    private final String message;
    private final String code;

    PaymentErrorCode(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
