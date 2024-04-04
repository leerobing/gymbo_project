package com.example.gymbo_back_end.payment.service;

import com.example.gymbo_back_end.core.entity.Payment;
import com.example.gymbo_back_end.payment.dto.PaymentSuccessDto;
import org.json.JSONException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Map;

public interface PaymentService {

    Payment requestTossPayment(Payment payment, String userEmail);

    PaymentSuccessDto tossPaymentSuccess(String paymentKey, String orderId, Long amount) throws JSONException;

    void tossPaymentFail(String code, String message, String orderId);

    Map cancelPaymentPoint(String userEmail, String paymentKey, String cancelReason) throws JSONException;

    Slice<Payment> findAllChargingHistories(String memberId, Pageable pageable);
}
