package com.example.gymbo_back_end.payment.controller;

import com.example.gymbo_back_end.core.commom.exception.payment.PaymentNotFoundException;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.payment.code.PaymentErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = PaymentController.class)
@Slf4j
public class PaymentExceptionController {

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<ResBodyModel> handlePaymentNotFoundException(PaymentNotFoundException e ) {
        log.error("[PaymentExceptionController]PaymentNotFoundException Message = {}, class = {}", e.getMessage(), e.getClass());
        return AetResponse.toResponse(PaymentErrorCode.PAYMENT_NOT_FOUND);
    }

}
