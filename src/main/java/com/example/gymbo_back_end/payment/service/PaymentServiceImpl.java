package com.example.gymbo_back_end.payment.service;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.MemberPoint;
import com.example.gymbo_back_end.core.entity.Payment;
import com.example.gymbo_back_end.member.dao.MemberDao;
import com.example.gymbo_back_end.member.dao.MemberPointDao;
import com.example.gymbo_back_end.payment.config.TossPaymentConfig;
import com.example.gymbo_back_end.payment.dao.PaymentDao;
import com.example.gymbo_back_end.payment.dto.PaymentSuccessDto;
import com.example.gymbo_back_end.payment.repository.PaymentRepository;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService{

    private final TossPaymentConfig tossPaymentConfig;
    private final MemberPointDao memberPointDao;
    private final MemberDao memberDao;
    private final PaymentDao paymentDao;


    @Override
    public Payment requestTossPayment(Payment payment, String userEmail) {
        Member member = memberDao.findByMemberId(userEmail);
        if (payment.getAmount() < 50) {
            throw new IllegalArgumentException("충전금액이 적습니다.");
        }
        payment.setMember(member);

//        Optional<MemberPoint> memberPoint = memberPointDao.optionalMemberPointFind(payment.getMember());
//
//        if (memberPoint.isPresent()){
//            Long point = memberPoint.get().getPoint() + payment.getAmount();
//            memberPoint.get().changePoint(point);
//        } else {
//            MemberPoint point = MemberPoint.builder().member(payment.getMember()).point(payment.getAmount()).build();
//            memberPointDao.memberPointSave(point);
//        }

        return paymentDao.save(payment);
    }

    @Transactional
    public PaymentSuccessDto tossPaymentSuccess(String paymentKey, String orderId, Long amount) throws JSONException {
        Payment payment = verifyPayment(orderId, amount);
        PaymentSuccessDto result = requestPaymentAccept(paymentKey, orderId, amount);
        payment.setPaymentKey(paymentKey);//추후 결제 취소 / 결제 조회
        payment.setPaySuccessYN(true);

        Optional<MemberPoint> memberPoint = memberPointDao.optionalMemberPointFind(payment.getMember());

        if (memberPoint.isPresent()){
            Long point = memberPoint.get().getPoint() + amount;
            memberPoint.get().changePoint(point);
        } else {
            MemberPoint point = MemberPoint.builder().member(payment.getMember()).point(amount).build();
            memberPointDao.memberPointSave(point);
        }

//        memberService.updateMemberCache(payment.getMember());
        return result;
    }

    public Payment verifyPayment(String orderId, Long amount) {
        Payment payment = paymentDao.findByOrderId(orderId);

        if (!payment.getAmount().equals(amount)) {
            throw new IllegalArgumentException(" 충전금액이 일치하지 않습니다.");
        }
        return payment;
    }
    @Transactional
    public PaymentSuccessDto requestPaymentAccept(String paymentKey, String orderId, Long amount) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHeaders();
        JSONObject params = new JSONObject();
        params.put("paymentKey", paymentKey);
        params.put("orderId", orderId);
        params.put("amount", amount);
        PaymentSuccessDto result = null;

        String u = TossPaymentConfig.URL + "confirm"; //"https://api.tosspayments.com/v1/payments/confirm"

//        HttpEntity<JSONObject> jsonObjectHttpEntity = new HttpEntity<>(params, headers);
//        log.info("jsonObjectHttpEntity : {}", jsonObjectHttpEntity);

        result = restTemplate.postForObject(u,
                new HttpEntity<>(params.toString(), headers),
                PaymentSuccessDto.class);

        return result;
    }

    private HttpHeaders getHeaders() {
       HttpHeaders headers = new HttpHeaders();
        String encodedAuthKey = new String(
                Base64.getEncoder().encode((tossPaymentConfig.getTestSecretApiKey() + ":").getBytes(StandardCharsets.UTF_8)));


        headers.setBasicAuth(encodedAuthKey);
       // headers.add("Authorization", "Basic "+encodedAuthKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }


    @Transactional
    public void tossPaymentFail(String code, String message, String orderId) {
        Payment payment = paymentDao.findByOrderId(orderId);
        payment.setPaySuccessYN(false);
        payment.setFailReason(message);
    }

    /**
     * paymenyKey와 유저 Email로 알맞은 payment객체 찾아옴
     *
     * payment객체의 충전금액이 환불금액 이상인지 확인 후,
     *
     * 취소 여부와 취소 이유,를 바꿔준 후 환불금액만큼 차감해준 후 tossPaymentCancel()메서드로 넘겨줌
     *
     * */

    @Transactional
    public Map cancelPaymentPoint(String userEmail, String paymentKey, String cancelReason) throws JSONException {
        Member member = memberDao.findByMemberId(userEmail);
        Payment payment = paymentDao.findByPaymentKeyAndMember(paymentKey, member);

        // 취소 할려는데 포인트가 그만큼 없으면 환불 몬하지~
        if (memberPointDao.memberPointFind(member).getPoint() >= payment.getAmount()) {
            payment.setCancelYN(true);
            payment.setCancelReason(cancelReason);
            memberPointDao.memberPointFind(member).changePoint(memberPointDao.memberPointFind(member).getPoint() - payment.getAmount());
            return tossPaymentCancel(paymentKey, cancelReason);
        }

        throw new IllegalArgumentException("취소하기엔 포인트가 충분하지 않다. ");
    }


    /**
     * HTTP 요청을 보내고 응답을 받기 위한 RestTemplate 인스턴스 생성
     *
     * getHeaders() 메서드를 호출하여 HTTP 요청에 필요한 헤더를 가져옴
     *
     * cancelReason을 포함하는 JSON 형식의 요청 파라미터를 생성후 params 객체에 추가
     *
     * postForObject 메서드를 통해 Toss Payment URL+ 결제 키(paymentKey)+ "/cancel" 경로로 param과 header를 포함한 HTTP POST 요청을 보냄
     * */
    public Map tossPaymentCancel(String paymentKey, String cancelReason) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHeaders();
        JSONObject params = new JSONObject();
        params.put("cancelReason", cancelReason);

        return restTemplate.postForObject(TossPaymentConfig.URL + paymentKey + "/cancel",
                new HttpEntity<>(params.toString(), headers),
                Map.class);
    }


    @Override
    public Slice<Payment> findAllChargingHistories(String memberId, Pageable pageable) {
        Member member = memberDao.findByMemberId(memberId);
        return paymentDao.findAllByMember(member,
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                        Sort.Direction.DESC, "paymentId")
        );
    }
}
