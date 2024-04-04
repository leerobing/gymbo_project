package com.example.gymbo_back_end.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberPointRequestDto {

    private String memberId;
    private Long point;

    @Builder
    public MemberPointRequestDto(String memberId, Long point) {
        this.memberId = memberId;
        this.point = point;
    }
}
