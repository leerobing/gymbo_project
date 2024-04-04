package com.example.gymbo_back_end.ticket.dao;

import com.example.gymbo_back_end.core.entity.DailyTicket;
import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.ticket.repository.DailyTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TicketJpaDao implements TicketDao{

    private final DailyTicketRepository dailyTicketRepository;
    @Override
    public DailyTicket save(DailyTicket dailyTicket) {
        return dailyTicketRepository.save(dailyTicket);
    }

    @Override
    public DailyTicket find(Long id) {
        DailyTicket dailyTicket = dailyTicketRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일일권 입니다."));
        return dailyTicket;
    }

    @Override
    public List<DailyTicket> findDailyTicketsByGym(Gym gym) {
        return dailyTicketRepository.findDailyTicketsByGym(gym);
    }

    @Override
    public List<DailyTicket> findByMember(Member member) {
        List<DailyTicket> byMember = dailyTicketRepository.findByMember(member);
        return byMember;
    }

}
