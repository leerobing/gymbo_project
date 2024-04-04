package com.example.gymbo_back_end.OrderItem.dto;

import com.example.gymbo_back_end.core.entity.DailyTicket;
import com.example.gymbo_back_end.core.entity.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponseDto {

    private Long orderItemSeq;

    private Order order;

    private DailyTicket dailyTicket;

    private int orderPrice; //주문 가격

    private int count; //주문 수량
}
