package com.example.gymbo_back_end.order.mapper;

import com.example.gymbo_back_end.core.entity.DailyTicket;
import com.example.gymbo_back_end.core.entity.Order;
import com.example.gymbo_back_end.order.dto.FindByMemberResponseDto;
import com.example.gymbo_back_end.ticket.dto.DailyTicketDto;

import java.util.List;

public interface OrderMapper {

    /**DailyTicket 객체를  DailyTicketDto로 변환*/
    DailyTicketDto toResponse(DailyTicket dailyTicket);

    /**List<Order> orders를 List<FindByMemberResponseDto>로 변환*/
    List<FindByMemberResponseDto> toResponse(List<Order> orders);

    /*** 주문 seq 로 주문 상품 조회*/
    List<FindByMemberResponseDto> toResponse(Order order);
}
