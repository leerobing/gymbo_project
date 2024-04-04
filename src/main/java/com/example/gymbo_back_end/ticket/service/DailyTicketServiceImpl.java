package com.example.gymbo_back_end.ticket.service;

import com.example.gymbo_back_end.OrderItem.dao.OrderItemDao;
import com.example.gymbo_back_end.Reservation.dao.ReservationDao;
import com.example.gymbo_back_end.core.entity.*;
import com.example.gymbo_back_end.gym.dao.GymDao;
import com.example.gymbo_back_end.member.dao.MemberDao;
import com.example.gymbo_back_end.order.dao.OrderDao;
import com.example.gymbo_back_end.order.dto.OrderRequestDto;
import com.example.gymbo_back_end.ticket.dao.TicketDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class DailyTicketServiceImpl implements DailyTicketService{

    private final TicketDao ticketDao;
    private final GymDao gymDao;
    private final ReservationDao reservationDao;
    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;
    private final MemberDao memberDao;


    @Override //주문시 티켓 생성
    public DailyTicket createdForOrder(OrderRequestDto orderRequestDto) {

        for (int i = 0; i < orderRequestDto.getOrderCount(); i++) {
            Gym gym = gymDao.findByGymName(orderRequestDto.getGymName());

            if (orderRequestDto.getStartTime() != null) {

                Reservation reservation = Reservation.createdReservation(orderRequestDto.getStartTime(),
                        orderRequestDto.getEndTime(),
                        orderRequestDto.getStartDay(),
                        gym);

                Member byMemberId = memberDao.findByMemberId(orderRequestDto.getMemberId());

                DailyTicket dailyTicket = DailyTicket.builder()
                        .member(byMemberId)
                        .reservation(reservation)
                        .dailyTicketPrice(orderRequestDto.getTicketPrice())
                        .dailyTicketUse(true)
                        .gym(gym)
                        .build();

                DailyTicket ticket = ticketDao.save(dailyTicket);
                return ticket;

            } else {
                Member byMemberId = memberDao.findByMemberId(orderRequestDto.getMemberId());
                DailyTicket dailyTicket = DailyTicket.builder()
                        .member(byMemberId)
                        .reservation(null)
                        .dailyTicketPrice(orderRequestDto.getTicketPrice())
                        .dailyTicketUse(true)
                        .gym(gym)
                        .build();
                DailyTicket ticket = ticketDao.save(dailyTicket);
                return ticket;

            }
        }
        return null;
    }


    //회원번호로 구매한 티켓 조회
    /**
     * 1. 회원번호로 주문 번호 조회
     * 2. 주문번호로 주문 상품 조회
     * 3. 해당 주문 상품에 티멧 번호 조회
     * 4. 티켓 정보 반환
     */
    @Override
    public List<DailyTicket> findByMember(Long memberSeq) {

        Member member = memberDao.find(memberSeq);
        List<DailyTicket> byMember = ticketDao.findByMember(member);
        return byMember;
    }

    /**
     * 운동시설로 티켓 조회
     * */
    @Override
    public List<DailyTicket> findDailyTicketsByGym(Gym gym) {
        return ticketDao.findDailyTicketsByGym(gym);
    }

    /**
     * 주문번호으로 티켓 조회
     *
     * */


}
