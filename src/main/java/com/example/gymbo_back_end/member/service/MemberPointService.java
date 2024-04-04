package com.example.gymbo_back_end.member.service;

import com.example.gymbo_back_end.core.entity.MemberPoint;
import com.example.gymbo_back_end.member.dto.MemberPointRequestDto;

import java.util.Optional;

public interface MemberPointService {

    MemberPoint memberPointSave(MemberPointRequestDto memberPointRequestDto);

    MemberPoint memberPointFind(String memberId);

    MemberPoint memberPointUpdate(MemberPointRequestDto memberPointRequestDto);
     Optional<MemberPoint> optionalMemberPointFind(String memberId);
}
