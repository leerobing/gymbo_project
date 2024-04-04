package com.example.gymbo_back_end.Reservation.mapper;

import com.example.gymbo_back_end.Reservation.dto.FindReservationResponseDto;
import com.example.gymbo_back_end.core.entity.Reservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationMapperImpl implements ReservationMapper {

    @Override
    public List<FindReservationResponseDto> toResponse(List<Reservation> reservationByStartDay){
        List<FindReservationResponseDto> responseDtoList = new ArrayList<>();

        for (Reservation reservation : reservationByStartDay) {
            FindReservationResponseDto responseDto = FindReservationResponseDto.buildDto(reservation);
            responseDtoList.add(responseDto);
        }

        return responseDtoList;

    }

}
