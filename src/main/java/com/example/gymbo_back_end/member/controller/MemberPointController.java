package com.example.gymbo_back_end.member.controller;

import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.MemberPoint;
import com.example.gymbo_back_end.member.dto.MemberPointRequestDto;
import com.example.gymbo_back_end.member.dto.response.ResponseMemberPointDto;
import com.example.gymbo_back_end.member.service.MemberPointService;
import com.example.gymbo_back_end.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/members")
public class MemberPointController {

    private final MemberPointService memberPointService;

    /**
     * 회원 포인트 충전 컨트롤러
     * */
    @PostMapping("/point_save")
    public ResponseEntity<ResBodyModel> memberPointSave(@RequestBody MemberPointRequestDto memberPointRequestDto) {
        MemberPoint memberPoint = memberPointService.memberPointSave(memberPointRequestDto);
        ResponseMemberPointDto responseMemberPointDto = ResponseMemberPointDto.buildDto(memberPoint);
        return AetResponse.toResponse(SuccessCode.SUCCESS,responseMemberPointDto);

    }

    /**
     * 회원 포인트 조회 컨트롤러
     * */
    @GetMapping("point_find/{memberId}")
    public ResponseEntity<ResBodyModel> memberPointFind(@PathVariable("memberId") String memberId) {

        MemberPoint memberPoint = memberPointService.memberPointFind(memberId);
        ResponseMemberPointDto responseMemberPointDto = ResponseMemberPointDto.buildDto(memberPoint);
        return AetResponse.toResponse(SuccessCode.SUCCESS,responseMemberPointDto);
    }

    /**
     * 회원 포인트 수정 컨트롤러
     * */
    @PostMapping ("/point_update")
    public ResponseEntity<ResBodyModel> memberPointUpdate(@RequestBody MemberPointRequestDto memberPointRequestDto) {

        MemberPoint memberPoint = memberPointService.memberPointUpdate(memberPointRequestDto);
        ResponseMemberPointDto responseMemberPointDto = ResponseMemberPointDto.buildDto(memberPoint);
        return AetResponse.toResponse(SuccessCode.SUCCESS,responseMemberPointDto);
    }
}
