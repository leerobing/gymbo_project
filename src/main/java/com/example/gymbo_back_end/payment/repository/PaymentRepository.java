package com.example.gymbo_back_end.payment.repository;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.Payment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrderId(String orderId);

    Optional<Payment> findByPaymentKeyAndMember(String paymentKey, Member member);

    Slice<Payment> findAllByMember(Member member, Pageable pageable);
}
