package com.example.gymbo_back_end.Reservation.dto;

import com.example.gymbo_back_end.core.entity.Reservation;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FindReservationResponseDto {

    private String gymName;
    private String startDay;
    private String startTime;

    public static FindReservationResponseDto buildDto(Reservation reservation) {
        FindReservationResponseDto reservationStartTimeResponseDto = new FindReservationResponseDto();
        reservationStartTimeResponseDto.setStartTime(reservation.getStartTime());
        reservationStartTimeResponseDto.setStartDay(reservation.getStartDay());
        reservationStartTimeResponseDto.setGymName(reservation.getGym().getGymName());
        return reservationStartTimeResponseDto;
    }
}

