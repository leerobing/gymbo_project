package com.example.gymbo_back_end.auth.dto;

import lombok.Data;

@Data
public class AuthLoginRequestDto {
    private String memberId;
    private String password;
}
