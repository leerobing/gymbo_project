package com.example.gymbo_back_end.auth.code;

import com.example.gymbo_back_end.core.commom.code.BodyCode;
import lombok.Getter;

@Getter
public enum JwtErrorCode implements BodyCode {

    NOT_EXIST_TOKEN_VALUE("TK01", "토큰 정보가 존재하지 않습니다."),
    INVALID_TOKEN("TK02", "토큰의 정보와 사용자 정보가 틀립니다."),
    TIMEOUT_TOKEN("TK03", "토큰의 유효 시간이 지났습니다.");

    private final String code;
    private final String message;

    JwtErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}