package com.example.gymbo_back_end.Reservation.dao;

import com.example.gymbo_back_end.Reservation.dto.ReservationDto;
import com.example.gymbo_back_end.Reservation.repository.ReservationRepository;
import com.example.gymbo_back_end.core.entity.Reservation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ReservationJpaDao implements ReservationDao{

    private final ReservationRepository reservationRepository;


    @Override
    public List<Reservation> findReservationsByStartDay(String startDay) {
        return reservationRepository.findReservationsByStartDay(startDay);
    }

    @Override
    public List<Reservation> findReservationsByStartTime(String startTime) {
        return reservationRepository.findReservationsByStartTime(startTime);
    }

    @Override
    public List<Reservation> findReservationsByStartDayAndGym(ReservationDto reservationDto) {
        return reservationRepository.findReservationsByStartDayAndGym(reservationDto);
    }


}
