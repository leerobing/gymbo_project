package com.example.gymbo_back_end.member.mapper;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.MemberPoint;
import com.example.gymbo_back_end.member.dto.response.ResponseMemberInfoDto;
import com.example.gymbo_back_end.member.service.MemberPointService;
import com.example.gymbo_back_end.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberMapper {

    private final MemberService memberService;
    private final MemberPointService memberPointService;

    /**
     * 로그인 시 필요한 응답 객체를 만들기 위한 로직
     * */
    public ResponseMemberInfoDto toResponse(Optional<MemberPoint> optionalMemberPointFind, Member member, List<Map<String, Object>> memberPhoto) {

        if (optionalMemberPointFind.isPresent()) {
            return ResponseMemberInfoDto.builder()
                    .memberId(member.getMemberId())
                    .nickName(member.getNickName())
                    .point(optionalMemberPointFind.get().getPoint())
                    .memberPhoto(memberPhoto)
                    .build();
        } else {
            return ResponseMemberInfoDto.builder()
                    .memberId(member.getMemberId())
                    .nickName(member.getNickName())
                    .point(null)
                    .memberPhoto(memberPhoto)
                    .build();
        }
    }

    /**
     * 로그인 시 필요한 응답 객체(프로필 사진 제외)를 만들기 위한 로직
     * */
    public ResponseMemberInfoDto toResponse(Optional<MemberPoint> optionalMemberPointFind, Member member) {

        if (optionalMemberPointFind.isPresent()) {
            return ResponseMemberInfoDto.builder()
                    .memberId(member.getMemberId())
                    .nickName(member.getNickName())
                    .point(optionalMemberPointFind.get().getPoint())
                    .build();
        } else {
            return ResponseMemberInfoDto.builder()
                    .memberId(member.getMemberId())
                    .nickName(member.getNickName())
                    .point(null)
                    .build();
        }
    }

    /**
     * 회원 전체 조회의 응답 객체
     * */
    public List<ResponseMemberInfoDto> toResponse(List<Member> members) {
        List<ResponseMemberInfoDto> responseMemberInfoDtos = new ArrayList<>();
        for (Member member : members) {
            Optional<MemberPoint> optionalMemberPointFind = memberPointService.optionalMemberPointFind(member.getMemberId());
            if (optionalMemberPointFind.isPresent()) {
                ResponseMemberInfoDto responseMemberInfoDto = ResponseMemberInfoDto.builder()
                        .memberId(member.getMemberId())
                        .nickName(member.getNickName())
                        .point(optionalMemberPointFind.get().getPoint())
                        .build();

                responseMemberInfoDtos.add(responseMemberInfoDto);
            } else {
                ResponseMemberInfoDto responseMemberInfoDto = ResponseMemberInfoDto.builder()
                        .memberId(member.getMemberId())
                        .nickName(member.getNickName())
                        .point(null)
                        .build();
                responseMemberInfoDtos.add(responseMemberInfoDto);
            }
        }
        return responseMemberInfoDtos;
    }
}
