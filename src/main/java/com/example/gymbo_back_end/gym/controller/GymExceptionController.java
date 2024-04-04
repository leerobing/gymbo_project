package com.example.gymbo_back_end.gym.controller;

import com.example.gymbo_back_end.core.commom.exception.gym.GymNotFoundException;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.gym.code.GymErrorCode;
import com.example.gymbo_back_end.member.code.MemberErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.NotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = GymController.class)
@Slf4j
public class GymExceptionController {

    @ExceptionHandler(GymNotFoundException.class)
    public ResponseEntity<ResBodyModel> handleGymNotFoundException(GymNotFoundException e) {
        log.error("[GymExceptionHandler]GymNotFoundException Message = {}, class = {}", e.getMessage(), e.getClass());
        return AetResponse.toResponse(GymErrorCode.GYM_NOT_FOUND);

    }
}
