package com.example.gymbo_back_end.ticket.repository;

import com.example.gymbo_back_end.core.entity.DailyTicket;
import com.example.gymbo_back_end.core.entity.Gym;
import com.example.gymbo_back_end.core.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DailyTicketRepository extends JpaRepository<DailyTicket, Long> {

    @Query("SELECT dt FROM DailyTicket dt WHERE dt.gym = :gym")
    List<DailyTicket> findDailyTicketsByGym(@Param("gym") Gym gym);

    @Query("SELECT dt FROM DailyTicket dt WHERE dt.member = :member")
    List<DailyTicket> findByMember(@Param("member") Member member);



}
