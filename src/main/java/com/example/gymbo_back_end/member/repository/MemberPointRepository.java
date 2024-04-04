package com.example.gymbo_back_end.member.repository;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.MemberPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberPointRepository extends JpaRepository<MemberPoint, Long> {
    @Query("SELECT mp FROM MemberPoint mp where mp.member =:member")
    Optional<MemberPoint> findMemberPointByMember(@Param("member") Member member);
}
