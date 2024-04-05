package com.example.gymbo_back_end.oauth.repository;

import com.example.gymbo_back_end.oauth.entity.OauthMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OauthMemberRepository extends JpaRepository<OauthMember, Long> {

    Optional<OauthMember> findByEmail(String email);
}
