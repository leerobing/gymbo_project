package com.example.gymbo_back_end.core.commom.response.model;


import lombok.Builder;
import lombok.Getter;

/**
 * Response Body 기본 베이스 코드이다.
 */
@Getter
public class ResBodyModel {
    private final String code;
    private final String description;
    private final String dateTime;
    private final Object data;
    @Builder
    public ResBodyModel(String code, String description, String dateTime, Object data) {
        this.code = code;
        this.description = description;
        this.dateTime = dateTime;
        this.data = data;
    }
}
