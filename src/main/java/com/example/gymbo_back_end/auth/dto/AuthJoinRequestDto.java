package com.example.gymbo_back_end.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthJoinRequestDto {

    private String memberId;

    private String password;

    private String nickName;
}
