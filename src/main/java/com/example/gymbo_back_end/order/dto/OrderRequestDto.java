package com.example.gymbo_back_end.order.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter @Setter
public class OrderRequestDto {

    private String memberId; //아이디
    private String nickName; //회원 닉네임
    private String ticketPrice; //티켓 가격
    private String gymName; //운동시설 이름
    private String startDay; //예약 시작 날짜
    private String startTime; // 예약 시작 시간
    private String endTime; // 예약 종료 시간

    private int orderCount; //주문 당시 수량


}
