package com.example.gymbo_back_end.auth.controller;

import com.example.gymbo_back_end.core.commom.exception.auth.IllegalAccessTokenException;
import com.example.gymbo_back_end.core.commom.exception.auth.InvalidPasswordException;
import com.example.gymbo_back_end.core.commom.exception.auth.MissingAuthorizationException;
import com.example.gymbo_back_end.core.commom.exception.member.MemberIdAlreadyExistsException;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.member.code.MemberErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.gymbo_back_end.auth.code.AuthErrorCode.INVALID_REQUEST_USER;
import static com.example.gymbo_back_end.auth.code.JwtErrorCode.NOT_EXIST_TOKEN_VALUE;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = AuthController.class)
public class AuthExceptionController {

    @ExceptionHandler(IllegalAccessTokenException.class)
    public ResponseEntity<ResBodyModel> handleIllegalAccessTokenExceptionException(IllegalAccessTokenException e) {
        log.error("[AuthExceptionHandler]IllegalAccessTokenException Message = {}, class = {}", e.getMessage(), e.getClass());
        return AetResponse.toResponse(INVALID_REQUEST_USER, e.getStatus());
    }

    @ExceptionHandler(MissingAuthorizationException.class)
    public ResponseEntity<ResBodyModel> handleMissingAuthorizationException(MissingAuthorizationException e) {
        log.error("[AuthExceptionHandler]MissingAuthorizationException Message = {}, class = {}", e.getMessage(), e.getClass());
        return AetResponse.toResponse(NOT_EXIST_TOKEN_VALUE,e.getMessage());

    }

    @ExceptionHandler(InvalidPasswordException.class) //비밀번호 인증 실패
    public ResponseEntity<ResBodyModel> handleInvalidPasswordException(InvalidPasswordException e) {
        log.error("[AuthExceptionHandler]InvalidPasswordException Message = {}, class = {}", e.getMessage(), e.getClass());
        return AetResponse.toResponse(INVALID_REQUEST_USER,e.getMessage());

    }
    @ExceptionHandler(MemberIdAlreadyExistsException.class)
    public ResponseEntity<ResBodyModel> handleMemberIdAlreadyExistsException(MemberIdAlreadyExistsException e) {

        log.error("[MemberExceptionHandler]MemberIdAlreadyExistsException Message = {}, class = {}", e.getMessage(), e.getClass());
        return AetResponse.toResponse(MemberErrorCode.Member_ID_ALREADY_EXISTS);
    }


}

