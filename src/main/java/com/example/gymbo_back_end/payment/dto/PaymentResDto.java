package com.example.gymbo_back_end.payment.dto;

import com.example.gymbo_back_end.core.entity.Order;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentResDto {
    private String payType;
    private Long amount;
    private String orderName;
    private String orderId;
    private String memberEmail;
    private String memberName;
    private String successUrl;
    private String failUrl;

    private String failReason;
    private boolean cancelYN;
    private String cancelReason;
    private String createdAt;
}
