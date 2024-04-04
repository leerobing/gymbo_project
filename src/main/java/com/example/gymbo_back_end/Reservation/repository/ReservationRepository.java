package com.example.gymbo_back_end.Reservation.repository;

import com.example.gymbo_back_end.Reservation.dto.ReservationDto;
import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.core.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.startDay = :startDay")
    List<Reservation> findReservationsByStartDay(@Param("startDay") String startDay);

    @Query("SELECT r FROM Reservation r WHERE r.startTime = :startTime")
    List<Reservation> findReservationsByStartTime(@Param("startTime") String startTime);

    @Query("SELECT r FROM Reservation r WHERE r.gym = :#{#dto.gym} AND r.startDay = :#{#dto.startDay}")
    List<Reservation> findReservationsByStartDayAndGym(@Param("dto") ReservationDto dto);


}
