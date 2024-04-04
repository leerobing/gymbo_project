package com.example.gymbo_back_end.payment.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true,access = AccessLevel.PROTECTED)
public class ChargingHistoryDto {

    private Long paymentHistoryId;
    @NonNull
    private Long amount;
    @NonNull
    private String orderName;

    private boolean isPaySuccessYN;

    private Date createdAt;
}
