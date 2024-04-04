package com.example.gymbo_back_end.member.repository;

import com.example.gymbo_back_end.core.entity.Member;
import com.example.gymbo_back_end.core.entity.MemberPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberPhotoRepository extends JpaRepository<MemberPhoto, Long> {

    @Query("SELECT mp FROM MemberPhoto mp WHERE mp.member = :member")
    List<MemberPhoto> findMemberPhotosByMember(@Param("member")Member member);
}
