package com.example.gymbo_back_end.member.service;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.MemberPoint;
import com.example.gymbo_back_end.member.dao.MemberDao;
import com.example.gymbo_back_end.member.dao.MemberPointDao;
import com.example.gymbo_back_end.member.dto.MemberPointRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberPointServiceImpl implements MemberPointService{

    private final MemberPointDao memberPointDao;
    private final MemberDao memberDao;

    /**
     * 회원 포인트 충전
     * */
    @Override
    public MemberPoint memberPointSave(MemberPointRequestDto memberPointRequestDto) {

        String memberId = memberPointRequestDto.getMemberId();
        Long point = memberPointRequestDto.getPoint();
        Member member = memberDao.findByMemberId(memberId);
        Optional<MemberPoint> optionalMemberPointFind = memberPointDao.optionalMemberPointFind(member);

        if (optionalMemberPointFind.isPresent()) {
            MemberPoint memberPoint = memberPointDao.memberPointFind(member);
            long updatePoint = memberPoint.getPoint() + point;
            memberPoint.changePoint(updatePoint);
            return memberPoint;
        }

        MemberPoint memberPoint = MemberPoint.builder()
                .point(point)
                .member(member)
                .build();
        memberPointDao.memberPointSave(memberPoint);

        return memberPoint;
    }

    /**
     * 회원 포인트 조회
     * */
    @Override
    public MemberPoint memberPointFind(String memberId) {
        Member member = memberDao.findByMemberId(memberId);
        MemberPoint memberPoint = memberPointDao.memberPointFind(member);
        return memberPoint;
    }

    /**
     * 로그인이 회원 조회 로직
     * */
    @Override
    public Optional<MemberPoint> optionalMemberPointFind(String memberId) {
        Member member = memberDao.findByMemberId(memberId);
        Optional<MemberPoint> optionalMemberPointFind = memberPointDao.optionalMemberPointFind(member);
        return optionalMemberPointFind;
    }

    /**
     * 회원 포인트 수정
     * */
    @Override
    public MemberPoint memberPointUpdate(MemberPointRequestDto memberPointRequestDto) {
        String memberId = memberPointRequestDto.getMemberId();
        Member member = memberDao.findByMemberId(memberId);
        MemberPoint memberPoint = memberPointDao.memberPointFind(member);
        memberPoint.changePoint(memberPoint.getPoint());
        return memberPoint;
    }


}
