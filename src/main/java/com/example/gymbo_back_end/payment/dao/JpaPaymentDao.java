package com.example.gymbo_back_end.payment.dao;

import com.example.gymbo_back_end.core.commom.exception.payment.PaymentNotFoundException;
import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.Payment;
import com.example.gymbo_back_end.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JpaPaymentDao implements PaymentDao{

    private final PaymentRepository paymentRepository;

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment findByOrderId(String orderId) {
        return paymentRepository.findByOrderId(orderId).orElseThrow(() -> new PaymentNotFoundException("payment 객체를 찾을 수 없습니다."));
    }

    @Override
    public Payment findByPaymentKeyAndMember(String paymentKey, Member member) {
        return paymentRepository.findByPaymentKeyAndMember(paymentKey,member).orElseThrow(() -> new PaymentNotFoundException("payment 객체를 찾을 수 없습니다."));
    }

    @Override
    public Slice<Payment> findAllByMember(Member member, Pageable pageable) {
        return paymentRepository.findAllByMember(member,pageable);
    }
}
