package com.example.gymbo_back_end.gym.code;

import com.example.gymbo_back_end.core.commom.code.BodyCode;
import lombok.Getter;

@Getter
public enum GymErrorCode implements BodyCode {
    GYM_NOT_FOUND("GYM01", "운동시설을 찾을 수 없습니다."),
    ;
    private final String message;
    private final String code;

    GymErrorCode(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
