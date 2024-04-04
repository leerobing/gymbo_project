package com.example.gymbo_back_end.member.dao;

import com.example.gymbo_back_end.core.commom.exception.member.MemberNotFoundException;
import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.member.dto.response.ResponseMemberInfoDto;
import com.example.gymbo_back_end.member.repository.MemberRepository;
import com.example.gymbo_back_end.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
@Slf4j
public class JpaMemberDao implements MemberDao{

    private final MemberRepository memberRepository;

    @Override
    public Member save(Member member) {

        Member savedMember = memberRepository.save(member);

        return savedMember;
    }

    @Override
    public Member findByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId).orElseThrow(() -> new MemberNotFoundException("존재하지 않은 회원입니다."));
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = memberRepository.findAll();
        return members;
    }

    @Override
    public void delete(String memberId) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));
        memberRepository.delete(member);
    }

    @Override
    public void delete(Long memberSeq) {
        Member member = memberRepository.findById(memberSeq).orElseThrow(() -> new MemberNotFoundException("존재하지 않는 회원입니다."));
        memberRepository.delete(member);
    }

    @Override
    public Member find(Long memberSeq) {
        Member member = memberRepository.findById(memberSeq).orElseThrow(() -> new MemberNotFoundException("사용자를 찾을 수 없습니다."));
        return member;
    }

    @Override
    public Boolean existsByMemberId(String memberId) {
        return memberRepository.existsByMemberId(memberId);
    }


}
