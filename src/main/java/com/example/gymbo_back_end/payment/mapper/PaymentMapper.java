package com.example.gymbo_back_end.payment.mapper;

import com.example.gymbo_back_end.core.entity.Payment;
import com.example.gymbo_back_end.payment.dto.ChargingHistoryDto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    default List<ChargingHistoryDto> chargingHistoryToChargingHistoryResponses(List<Payment> chargingHistories) {
        if (chargingHistories == null) {
            return null;
        }

        return chargingHistories.stream()
                .map(chargingHistory -> {
                    return ChargingHistoryDto.builder()
                            .paymentHistoryId(chargingHistory.getPaymentId())
                            .amount(chargingHistory.getAmount())
                            .orderName(chargingHistory.getOrderName())
                            .createdAt(chargingHistory.getCreatedAt())
                            .isPaySuccessYN(chargingHistory.isPaySuccessYN())
                            .build();
                }).collect(Collectors.toList());
    }
}
