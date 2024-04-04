package com.example.gymbo_back_end.member.controller;


import com.example.gymbo_back_end.core.commom.exception.member.MemberIdAlreadyExistsException;
import com.example.gymbo_back_end.core.commom.exception.member.MemberNotFoundException;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.member.code.MemberErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = MemberController.class)
public class MemberExceptionController {

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ResBodyModel> handleMemberNotFoundException(MemberNotFoundException e) {
        log.error("[MemberExceptionHandler]MemberNotFoundException Message = {}, class = {}", e.getMessage(), e.getClass());
        return AetResponse.toResponse(MemberErrorCode.Member_NOT_FOUND);
    }

}
