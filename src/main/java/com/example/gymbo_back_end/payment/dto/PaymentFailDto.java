package com.example.gymbo_back_end.payment.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentFailDto {
    String errorCode;
    String errorMessage;
    String orderId;
}