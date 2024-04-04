package com.example.gymbo_back_end.order.service;

import com.example.gymbo_back_end.core.entity.*;
import com.example.gymbo_back_end.core.entity.DailyTicket;
import com.example.gymbo_back_end.member.dao.MemberDao;
import com.example.gymbo_back_end.member.dao.MemberPointDao;
import com.example.gymbo_back_end.order.dao.OrderDao;
import com.example.gymbo_back_end.order.dto.FindOneResponseDto;
import com.example.gymbo_back_end.order.dto.OrderRequestDto;
import com.example.gymbo_back_end.order.dto.OrderResponseDto;
import com.example.gymbo_back_end.ticket.dao.TicketDao;
import com.example.gymbo_back_end.ticket.dto.DailyTicketDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService{

    private final TicketDao ticketDao;
    private final MemberDao memberDao;
    private final OrderDao orderDao;
    private final MemberPointDao memberPointDao;

    /**
     * 주문 시 주문한 회원의 포인트를 참가하고 주문 정보를 Database 에 저장.
     * */
    @Override
    public OrderResponseDto save(OrderRequestDto orderRequestDto, DailyTicketDto dailyTicketDto) {
        String memberId = orderRequestDto.getMemberId();
        Member member = memberDao.findByMemberId(memberId);
        DailyTicket ticket = ticketDao.find(dailyTicketDto.getTicketSeq());
        OrderItem orderItem = OrderItem.createOrderItem(ticket, orderRequestDto.getOrderCount());

        MemberPoint memberPoint = memberPointDao.memberPointFind(member);

        if (memberPoint.getPoint() < orderItem.getOrderPrice()){ // 주문 금액보다 소유하고 있는 포인트가 적을 시 예외 발생.
            throw new IllegalArgumentException("포인트가 부족합니다.");
        }

        long result = memberPoint.getPoint() - orderItem.getOrderPrice();
        memberPoint.changePoint(result); // 주문 금액에 맞게 회원 포인트 차감
        Order order = Order.createdOrder(member,orderItem);

        Order saveOrder = orderDao.save(order);

        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .orderSeq(saveOrder.getOrderSeq())
                .memberSeq(saveOrder.getMember().getMemberSeq())
                .memberId(saveOrder.getMember().getMemberId())
                .nickName(saveOrder.getMember().getNickName())
                .build();

        return orderResponseDto;
    }

    @Override //주문 번호로 주문 찾기
    public Order find(Long orderSeq) {
        return orderDao.findOne(orderSeq);
    }

    @Override //주문 번호로 멤버 조회
    public FindOneResponseDto OrderFindMember(Long orderSeq) {
        Order order = orderDao.findOne(orderSeq);
        FindOneResponseDto orderFindOneResponseDto = FindOneResponseDto.buildDto(order);

        return orderFindOneResponseDto;
    }

    @Override// 멤버 seq 로 주문 조회
    public List<Order> memberFindOrders(Long memberSeq) {
        Member member = memberDao.find(memberSeq);
        List<Order> ordersByMember = orderDao.findOrdersByMember(member);
        return ordersByMember;
    }

    @Override// 멤버 아이디 로 주문 조회
    public List<Order> memberFindOrders(String memberId) {
        Member member = memberDao.findByMemberId(memberId);
        List<Order> ordersByMember = orderDao.findOrdersByMember(member);
        return ordersByMember;
    }
}


