package com.example.gymbo_back_end.ticket.controller;

import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.core.entity.DailyTicket;
import com.example.gymbo_back_end.ticket.dto.FindByMemberRequestDto;
import com.example.gymbo_back_end.ticket.dto.FindByMemberResponseDto;
import com.example.gymbo_back_end.ticket.service.DailyTicketService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
@Slf4j
public class DailyTicketController {

    private final DailyTicketService dailyTicketService;


    @PostMapping("/member_seq") //회원으로 티켓 조회
    public ResponseEntity<ResBodyModel> findByMember(@RequestBody FindByMemberRequestDto dto){

        Long memberSeq = dto.getMemberSeq();
        List<DailyTicket> byMember = dailyTicketService.findByMember(memberSeq);
        List<FindByMemberResponseDto> responseDtoList = new ArrayList<>();

        for (DailyTicket dailyTicket : byMember) {
            FindByMemberResponseDto responseDto = new FindByMemberResponseDto();
            responseDto.setGymName(dailyTicket.getGym().getGymName());
            responseDto.setGymSports(dailyTicket.getGym().getGymSports());
            responseDto.setDailyTicketUse(dailyTicket.getDailyTicketUse());
            responseDto.setDailyTicketSeq(dailyTicket.getDailyTicketSeq());
            responseDtoList.add(responseDto);
        }

        return AetResponse.toResponse(SuccessCode.SUCCESS,responseDtoList);
    }

    /**
     * 주문번호으로 티켓 조회
     * */


}
