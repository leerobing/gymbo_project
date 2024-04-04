package com.example.gymbo_back_end.payment.controller;

import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.dto.SliceInfo;
import com.example.gymbo_back_end.core.commom.response.dto.SliceResponseDto;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.core.entity.Payment;
import com.example.gymbo_back_end.payment.config.TossPaymentConfig;
import com.example.gymbo_back_end.payment.dto.*;
import com.example.gymbo_back_end.payment.mapper.PaymentMapper;
import com.example.gymbo_back_end.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Validated
@RequestMapping("/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final TossPaymentConfig tossPaymentConfig;
    private final PaymentMapper paymentMapper;

    @PostMapping("/toss")
    public ResponseEntity<ResBodyModel> requestTossPayment(@AuthenticationPrincipal UserDetails principal, @RequestBody PaymentDto paymentReqDto) {

        Payment payment = paymentReqDto.toEntity();
        PaymentResDto paymentResDto = paymentService.requestTossPayment(payment, principal.getUsername()).toPaymentResDto();
        paymentResDto.setSuccessUrl(paymentReqDto.getYourSuccessUrl() == null ? tossPaymentConfig.getSuccessUrl() : paymentReqDto.getYourSuccessUrl());
        paymentResDto.setFailUrl(paymentReqDto.getYourFailUrl() == null ? tossPaymentConfig.getFailUrl() : paymentReqDto.getYourFailUrl());
        return AetResponse.toResponse(SuccessCode.SUCCESS,paymentResDto);
    }

    @GetMapping("/toss/success")
    public ResponseEntity<ResBodyModel> tossPaymentSuccess(
            @RequestParam String paymentKey,
            @RequestParam String orderId,
            @RequestParam Long amount
    ) throws JSONException {

        PaymentSuccessDto paymentSuccessDto = paymentService.tossPaymentSuccess(paymentKey, orderId, amount);

        return AetResponse.toResponse(SuccessCode.SUCCESS, paymentSuccessDto);
    }
    @GetMapping("/toss/fail")
    public ResponseEntity<ResBodyModel> tossPaymentFail(
            @RequestParam String code,
            @RequestParam String message,
            @RequestParam String orderId
    ) {
        paymentService.tossPaymentFail(code, message, orderId);

        PaymentFailDto failDto = PaymentFailDto.builder()
                .errorCode(code)
                .errorMessage(message)
                .orderId(orderId)
                .build();

        return AetResponse.toResponse(SuccessCode.SUCCESS,failDto);
    }


    @PostMapping("/toss/cancel/point")
    public ResponseEntity<ResBodyModel> tossPaymentCancelPoint(
            @AuthenticationPrincipal User principal,
            @RequestParam String paymentKey,
            @RequestParam String cancelReason
    ) throws JSONException {

        Map map = paymentService.cancelPaymentPoint(principal.getUsername(), paymentKey, cancelReason);
        return AetResponse.toResponse(SuccessCode.SUCCESS,map);
    }

    @GetMapping("/toss/history")
    public ResponseEntity getChargingHistory(@AuthenticationPrincipal User authMember,
                                             Pageable pageable) {
        Slice<Payment> chargingHistories = paymentService.findAllChargingHistories(authMember.getUsername(), pageable);
        SliceInfo sliceInfo = new SliceInfo(pageable, chargingHistories.getNumberOfElements(), chargingHistories.hasNext());

        return new ResponseEntity<>(
                new SliceResponseDto<>(paymentMapper.chargingHistoryToChargingHistoryResponses(chargingHistories.getContent()), sliceInfo), HttpStatus.OK);
    }
}