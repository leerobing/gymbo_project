package com.example.gymbo_back_end.Reservation.service;

import com.example.gymbo_back_end.Reservation.dao.ReservationDao;
import com.example.gymbo_back_end.Reservation.dto.ReservationDto;
import com.example.gymbo_back_end.core.entity.Reservation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationDao reservationDao;

    @Override
    public List<Reservation> findReservationByStartDay(String startDay) { // 시작 날짜로 예약 조회
        List<Reservation> reservationByStartDay = reservationDao.findReservationsByStartDay(startDay);
        log.info("reservationByStartDay = {}",reservationByStartDay);
        return reservationByStartDay;
    }

    @Override
    public List<Reservation> findReservationByStartTime(String startTime) { //시작 시간으로 조회
        List<Reservation> reservationsByStartTime = reservationDao.findReservationsByStartTime(startTime);
        return reservationsByStartTime;
    }

    @Override
    public List<Reservation> findReservationsByStartDayAndGym(ReservationDto reservationDto) {
        List<Reservation> reservationsByStartTimeAndGym = reservationDao.findReservationsByStartDayAndGym(reservationDto);
        return reservationsByStartTimeAndGym;
    }

}
