package com.example.gymbo_back_end.member.dao;

import com.example.gymbo_back_end.core.commom.exception.member.MemberPointNotFoundException;
import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.MemberPhoto;
import com.example.gymbo_back_end.core.entity.MemberPoint;
import com.example.gymbo_back_end.member.repository.MemberPointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JpaMemberPointDao implements MemberPointDao {

    private final MemberPointRepository memberPointRepository;

    @Override
    public MemberPoint memberPointSave(MemberPoint memberPoint) {

        MemberPoint saved = memberPointRepository.save(memberPoint);

        return saved;
    }

    @Override
    public MemberPoint memberPointFind(Member member) {
        MemberPoint memberPoint = memberPointRepository.findMemberPointByMember(member).orElseThrow(() -> new MemberPointNotFoundException("포인트가 없습니다."));
        return memberPoint;
    }

    /**
     * Optional<MemberPoint> 타입으로 반환받기 위한 조회 메서드
     * */
    @Override
    public Optional<MemberPoint> optionalMemberPointFind(Member member) {
        Optional<MemberPoint> memberPoint = memberPointRepository.findMemberPointByMember(member);
        return memberPoint;
    }

}
