package com.example.gymbo_back_end.member.dao;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.MemberPhoto;
import com.example.gymbo_back_end.member.repository.MemberPhotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JpaMemberPhotoDao implements MemberPhotoDao{

    private final MemberPhotoRepository memberPhotoRepository;

    @Override
    public MemberPhoto saveMemberPhoto(MemberPhoto memberPhoto) {
         return memberPhotoRepository.save(memberPhoto);
    }

    @Override
    public List<MemberPhoto> findMemberPhotosByMember(Member member) {
        return memberPhotoRepository.findMemberPhotosByMember(member);
    }

    @Override
    public void memberPhotoDelete(MemberPhoto memberPhoto) {
        memberPhotoRepository.delete(memberPhoto);
    }
}
