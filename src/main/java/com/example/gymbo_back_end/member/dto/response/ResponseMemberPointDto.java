package com.example.gymbo_back_end.member.dto.response;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.MemberPoint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMemberPointDto {

    private Long memberPointSeq;
    private Long memberSeq;

    private String memberId;
    private String memberNickname;
    private Long point;


    public static ResponseMemberPointDto buildDto(MemberPoint memberPoint) {
        ResponseMemberPointDto responseMemberPointDto = new ResponseMemberPointDto();
        responseMemberPointDto.memberPointSeq = memberPoint.getMemberPointSeq();
        responseMemberPointDto.point = memberPoint.getPoint();

        Member member = memberPoint.getMember();
        responseMemberPointDto.memberSeq = member.getMemberSeq();
        responseMemberPointDto.memberId = member.getMemberId();
        responseMemberPointDto.memberNickname = member.getNickName();

        return responseMemberPointDto;

    }
}
