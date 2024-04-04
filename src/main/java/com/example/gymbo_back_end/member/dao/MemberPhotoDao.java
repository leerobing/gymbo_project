package com.example.gymbo_back_end.member.dao;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.MemberPhoto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberPhotoDao {

    MemberPhoto saveMemberPhoto(MemberPhoto memberPhoto);

    List<MemberPhoto> findMemberPhotosByMember(Member member);
    void memberPhotoDelete(MemberPhoto memberPhoto);
}
