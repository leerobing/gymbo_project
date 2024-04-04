package com.example.gymbo_back_end.member.repository;


import com.example.gymbo_back_end.core.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(String userEmail);

    Boolean existsByMemberId(String memberId);

}
