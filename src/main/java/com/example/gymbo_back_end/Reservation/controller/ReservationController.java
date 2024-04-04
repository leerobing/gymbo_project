package com.example.gymbo_back_end.Reservation.controller;

import com.example.gymbo_back_end.Reservation.dto.*;
import com.example.gymbo_back_end.Reservation.mapper.ReservationMapper;
import com.example.gymbo_back_end.Reservation.service.ReservationService;
import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.core.entity.Reservation;
import com.example.gymbo_back_end.gym.service.GymService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;
    private final GymService gymService;
    @PostMapping("/start_day") // 시작 날짜로 예약 조회
    public ResponseEntity<ResBodyModel> findReservationByStartDay(@RequestBody FindStartDayRequestDto reservationFindStartDayRequestDto){
        String startDay = reservationFindStartDayRequestDto.getStartDay();
        List<Reservation> reservationByStartDay = reservationService.findReservationByStartDay(startDay);
        List<FindReservationResponseDto> responseDtoList = reservationMapper.toResponse(reservationByStartDay);
        return AetResponse.toResponse(SuccessCode.SUCCESS,responseDtoList);
    }

    @PostMapping("/start_time") //시작 시간으로 조회
    public ResponseEntity<ResBodyModel> findReservationByStartTime(@RequestBody FindStartTimeRequestDto reservationFindStartTimeRequestDto) {

        String startTime = reservationFindStartTimeRequestDto.getStartTime();
        List<Reservation> reservationByStartTime = reservationService.findReservationByStartTime(startTime);
        List<FindReservationResponseDto> responseDtoList = reservationMapper.toResponse(reservationByStartTime);
        return AetResponse.toResponse(SuccessCode.SUCCESS,responseDtoList);
    }
    @PostMapping("/gym_seq/start_day")//요청받은 gymSeq와 startDay를 통해 예약 현황 조회
    public ResponseEntity<ResBodyModel> findReservationByTest(@RequestBody FindStartDayAndGymRequestDto dto) {

        Gym gym = gymService.find(dto.getGymSeq());
        ReservationDto reservationDto = ReservationDto.buildDto(gym, dto.getStartDay());
        List<Reservation> reservationsByStartTimeAndGymList = reservationService.findReservationsByStartDayAndGym(reservationDto);
        List<FindReservationResponseDto> responseDtoList = reservationMapper.toResponse(reservationsByStartTimeAndGymList);

        return AetResponse.toResponse(SuccessCode.SUCCESS,responseDtoList);
    }

}
