package com.example.gymbo_back_end.ticket.service;

import com.example.gymbo_back_end.core.entity.DailyTicket;

import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.order.dto.OrderRequestDto;

import java.util.List;

public interface DailyTicketService {

    DailyTicket createdForOrder(OrderRequestDto orderRequestDto);

    List<DailyTicket> findByMember(Long memberSeq);

    List<DailyTicket> findDailyTicketsByGym(Gym gym);

}
