package com.example.gymbo_back_end.member.service;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.auth.dto.AuthJoinRequestDto;
import com.example.gymbo_back_end.core.entity.MemberPhoto;
import com.example.gymbo_back_end.member.dto.MemberPhotoRequestDto;
import com.example.gymbo_back_end.member.dto.MemberRequestDto;
import com.example.gymbo_back_end.member.dto.response.ResponseMemberInfoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MemberService {


    Member find(String userEmail);

    Member find(Long memberSeq);

    List<Member> findAll();

    Member update(MemberRequestDto memberRequestDto);

    void delete(String memberId);

    void delete(Long memberSeq);

    List<MemberPhoto> saveMemberPhoto(MemberPhotoRequestDto memberPhotoRequestDto,List<MultipartFile> files) throws Exception;

    List<MemberPhoto> findMemberPhoto(Long memberSeq);

    List<MemberPhoto> findMemberPhoto(String memberId);

    void memberPhotoDelete(MemberPhoto memberPhoto);
}
