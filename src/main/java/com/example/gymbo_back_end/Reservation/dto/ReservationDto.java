package com.example.gymbo_back_end.Reservation.dto;

import com.example.gymbo_back_end.core.entity.Gym;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDto {

    private Gym gym;
    private String StartDay;


    public static ReservationDto buildDto(Gym gym, String startDay) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setGym(gym);
        reservationDto.setStartDay(startDay);
        return reservationDto;
    }

}
