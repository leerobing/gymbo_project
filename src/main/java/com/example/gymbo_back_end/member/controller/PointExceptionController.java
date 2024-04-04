package com.example.gymbo_back_end.member.controller;

import com.example.gymbo_back_end.core.commom.exception.member.MemberPointNotFoundException;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.member.code.MemberErrorCode;
import com.example.gymbo_back_end.member.service.MemberPointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = MemberPointController.class)
@Slf4j
public class PointExceptionController {
    @ExceptionHandler(MemberPointNotFoundException.class)
    public ResponseEntity<ResBodyModel> handleMemberPointNotFoundException(MemberPointNotFoundException e) {
        log.error("[PointExceptionController]MemberPointNotFoundException Message = {}, class = {}", e.getMessage(), e.getClass());
        return AetResponse.toResponse(MemberErrorCode.POINT_NOT_FOUND);
    }
}
