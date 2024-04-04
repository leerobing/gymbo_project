package com.example.gymbo_back_end.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDto {

    private String memberId;

    private String password;

    private String nickName;
}
