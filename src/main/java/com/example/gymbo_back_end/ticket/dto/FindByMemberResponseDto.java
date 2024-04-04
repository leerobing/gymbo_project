package com.example.gymbo_back_end.ticket.dto;

import com.example.gymbo_back_end.core.entity.Gym;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindByMemberResponseDto {

    private String GymName;
    private String GymSports;
    private Long dailyTicketSeq;
    private Boolean dailyTicketUse;

}
