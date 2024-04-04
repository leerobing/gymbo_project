package com.example.gymbo_back_end.ticket.dto;


import com.example.gymbo_back_end.core.commom.response.Address;
import com.example.gymbo_back_end.core.entity.Gym;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;

@Getter
@Setter
public class DailyTicketRequestDto {

    private String gymName;

    private Address gymAddress;

    private String gymNumber;

    private String managerNumber;

    private String dailyTicketPrice;

    private Boolean dailyTicketUse;


}
