package com.example.gymbo_back_end.auth.code;

import com.example.gymbo_back_end.core.commom.code.BodyCode;
import lombok.Getter;

@Getter
public enum AuthErrorCode implements BodyCode {
    FAILED_AUTH("AUTH01", "인증에 실패했습니다."),
    INVALID_REQUEST_USER("AUTH02", "요청 정보가 올바르지 않습니다."),
    INVALID_REFRESH_TOKEN("AUTH04", "올바르지 않은 리프레쉬 토큰입니다."),
    TIMEOUT_TOKEN("AUTH05", "토큰의 유효시간이 지났습니다."),
    INVALID_REQUEST_TOKEN("AUTH05", "올바르지 않은 토큰 형식입니다."),
    ;

    private final String code;
    private final String message;

    AuthErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
