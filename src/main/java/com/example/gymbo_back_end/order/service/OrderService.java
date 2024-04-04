package com.example.gymbo_back_end.order.service;

import com.example.gymbo_back_end.core.entity.Order;
import com.example.gymbo_back_end.order.dto.FindOneResponseDto;
import com.example.gymbo_back_end.order.dto.OrderRequestDto;
import com.example.gymbo_back_end.order.dto.OrderResponseDto;
import com.example.gymbo_back_end.ticket.dto.DailyTicketDto;

import java.util.List;

public interface OrderService {


    OrderResponseDto save(OrderRequestDto orderRequestDto, DailyTicketDto dailyTicketDto);

    Order find(Long orderSeq);

    FindOneResponseDto OrderFindMember(Long orderSeq);


    List<Order> memberFindOrders(Long memberSeq);

    List<Order> memberFindOrders(String memberId);
}
