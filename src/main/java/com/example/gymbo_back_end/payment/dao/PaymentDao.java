package com.example.gymbo_back_end.payment.dao;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.Payment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface PaymentDao {

    Payment save(Payment payment);
    Payment findByOrderId(String orderId);

    Payment findByPaymentKeyAndMember(String paymentKey, Member member);

    Slice<Payment> findAllByMember(Member member, Pageable pageable);
}
