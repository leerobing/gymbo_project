package com.example.gymbo_back_end.member.dao;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.member.dto.response.ResponseMemberInfoDto;

import java.util.List;

public interface MemberDao {

    Member save(Member member);

    Member findByMemberId(String memberId);

    List<Member> findAll();

    void delete(String memberId) ;

    void delete(Long memberSeq) ;

    Member find(Long memberSeq);

    Boolean existsByMemberId(String memberId);
}
