package com.example.gymbo_back_end.Reservation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindStartDayAndGymRequestDto {

    private Long gymSeq;
    private String startDay;
}
