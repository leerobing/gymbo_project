package com.example.gymbo_back_end.order.controller;

import com.example.gymbo_back_end.OrderItem.service.OrderItemService;
import com.example.gymbo_back_end.core.commom.code.SuccessCode;
import com.example.gymbo_back_end.core.commom.response.AetResponse;
import com.example.gymbo_back_end.core.commom.response.model.ResBodyModel;
import com.example.gymbo_back_end.core.entity.*;
import com.example.gymbo_back_end.order.dto.FindOneResponseDto;
import com.example.gymbo_back_end.order.dto.OrderRequestDto;
import com.example.gymbo_back_end.order.dto.OrderResponseDto;
import com.example.gymbo_back_end.order.dto.FindByMemberResponseDto;
import com.example.gymbo_back_end.order.mapper.OrderMapper;
import com.example.gymbo_back_end.order.service.OrderService;
import com.example.gymbo_back_end.ticket.dto.DailyTicketDto;
import com.example.gymbo_back_end.ticket.service.DailyTicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final DailyTicketService dailyTicketService;
    private final OrderMapper orderMapper;

    /**
     * 주문 저장
     * */
    @PostMapping
    public ResponseEntity<ResBodyModel> orderSave(@RequestBody OrderRequestDto orderRequestDto) {

        DailyTicket dailyTicket = dailyTicketService.createdForOrder(orderRequestDto);//티켓을 생성
        DailyTicketDto dailyTicketDto = orderMapper.toResponse(dailyTicket); //티켓 dto로 변환
        OrderResponseDto orderResponseDto = orderService.save(orderRequestDto, dailyTicketDto);
        return AetResponse.toResponse(SuccessCode.SUCCESS,orderResponseDto);

    }

    /**
     * 주문 seq 로 회원 조회
     * */
    @GetMapping("/{orderSeq}")
    public ResponseEntity<ResBodyModel> orderFindMember(@PathVariable Long orderSeq) {
        FindOneResponseDto findOneResponseDto = orderService.OrderFindMember(orderSeq);
        return AetResponse.toResponse(SuccessCode.SUCCESS,findOneResponseDto);
    }

    /**
     *  회원 seq 로 주문 조회
     * */
    @GetMapping("/memberSeq/{memberSeq}")
    public ResponseEntity<ResBodyModel> memberFindOrder(@PathVariable Long memberSeq){
        List<Order> orders = orderService.memberFindOrders(memberSeq);
        List<FindByMemberResponseDto> response = orderMapper.toResponse(orders);
        return AetResponse.toResponse(SuccessCode.SUCCESS,response);
    }


    /**
     *  회원 아이디 로 주문 조회
     * */
    @GetMapping("/memberId/{memberId}")
    public ResponseEntity<ResBodyModel> memberIdFindOrder(@PathVariable String memberId){
        List<Order> orders = orderService.memberFindOrders(memberId);
        List<FindByMemberResponseDto> response = orderMapper.toResponse(orders);
        return AetResponse.toResponse(SuccessCode.SUCCESS,response);
    }

    /**
     * 주문 seq 로 주문 상품 조회
     * */
    @GetMapping("/order_item/{orderSeq}")
    public ResponseEntity<ResBodyModel> orderItemFindOrder(@PathVariable Long orderSeq) {
        Order order = orderService.find(orderSeq);
        List<FindByMemberResponseDto> response = orderMapper.toResponse(order);
        return AetResponse.toResponse(SuccessCode.SUCCESS,response);
    }



}
