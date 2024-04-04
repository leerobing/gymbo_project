package com.example.gymbo_back_end.member.dao;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.MemberPoint;

import java.util.Optional;

public interface MemberPointDao {

    MemberPoint memberPointSave(MemberPoint memberPoint);

    MemberPoint memberPointFind(Member member);

    Optional<MemberPoint> optionalMemberPointFind(Member member);
}
