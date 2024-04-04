package com.example.gymbo_back_end.ticket.dto;

import com.example.gymbo_back_end.core.commom.response.Address;
import com.example.gymbo_back_end.core.entity.DailyTicket;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DailyTicketDto {

    private Long ticketSeq;

    private String gymName;

    private Address gymAddress;

    private String gymNumber;

    private String managerNumber;

    private String dailyTicketPrice;

    private Boolean dailyTicketUse;

}
