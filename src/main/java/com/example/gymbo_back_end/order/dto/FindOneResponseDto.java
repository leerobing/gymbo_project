package com.example.gymbo_back_end.order.dto;

import com.example.gymbo_back_end.core.entity.Order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindOneResponseDto {


    private Long orderSeq;
    private Long memberSeq; //주문 회원

    public static FindOneResponseDto buildDto(Order order){
        FindOneResponseDto orderFindOneResponseDto = new FindOneResponseDto();
        orderFindOneResponseDto.setMemberSeq(order.getMember().getMemberSeq());
        orderFindOneResponseDto.setOrderSeq(order.getOrderSeq());

        return orderFindOneResponseDto;
    }

}
