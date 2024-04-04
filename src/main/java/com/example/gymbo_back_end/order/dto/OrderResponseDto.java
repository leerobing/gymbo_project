package com.example.gymbo_back_end.order.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderResponseDto {

    private Long orderSeq;
    private Long memberSeq;
    private String memberId;
    private String nickName;


}
