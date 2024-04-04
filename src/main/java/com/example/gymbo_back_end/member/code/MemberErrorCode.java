package com.example.gymbo_back_end.member.code;

import com.example.gymbo_back_end.core.commom.code.BodyCode;
import lombok.Getter;

@Getter
public enum MemberErrorCode implements BodyCode {
    Member_NOT_FOUND("MEM01", "유저를 찾을 수 없습니다."),
    Member_ID_ALREADY_EXISTS("MEM02","아이디가 이미 존재합니다."),
    POINT_NOT_FOUND("MEM03","포인트를 찾을 수 없습니다.")
    ;

    private final String message;
    private final String code;

    MemberErrorCode(String message, String code) {
        this.message = message;
        this.code = code;
    }


}
