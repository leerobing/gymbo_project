package com.example.gymbo_back_end.Reservation.dao;

import com.example.gymbo_back_end.Reservation.dto.ReservationDto;
import com.example.gymbo_back_end.core.entity.Reservation;

import java.util.List;

public interface ReservationDao {

    List<Reservation> findReservationsByStartDay(String startDay);

    List<Reservation> findReservationsByStartTime(String startTime);

    List<Reservation> findReservationsByStartDayAndGym(ReservationDto reservationDto);
}
