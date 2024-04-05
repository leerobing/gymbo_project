package com.example.gymbo_back_end.oauth.service;


import com.example.gymbo_back_end.oauth.dto.authInfoResponse.OAuthInfoResponse;
import com.example.gymbo_back_end.oauth.dto.oAuthLoginParams.OAuthLoginParams;
import com.example.gymbo_back_end.oauth.entity.OauthMember;
import com.example.gymbo_back_end.oauth.jwt.AuthTokens;
import com.example.gymbo_back_end.oauth.jwt.AuthTokensGenerator;
import com.example.gymbo_back_end.oauth.repository.OauthMemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuthLoginService {

    private final OauthMemberRepository oauthMemberRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        log.info("oAuthInfoResponse.mail : {}",oAuthInfoResponse.getEmail());
        log.info("oAuthInfoResponse.nickname : {}",oAuthInfoResponse.getNickname());
        log.info("oAuthInfoResponse.oauth : {}",oAuthInfoResponse.getOAuthProvider());
        Long memberId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return oauthMemberRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(OauthMember::getId)
                .orElseGet(() -> {
                    try {
                        return newMember(oAuthInfoResponse);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public static String uniqueString(int digit) throws Exception {
        if(digit < 1) throw new Exception("can't generate");
        String random = UUID.randomUUID().toString();
        random = random.replaceAll("-", "");
        random = random.substring(0, digit);
        return random;
    }
    private Long newMember(OAuthInfoResponse oAuthInfoResponse) throws Exception {

        String uniqued = uniqueString(6);

        String nickname = oAuthInfoResponse.getNickname();
        String uuidNickName = nickname + uniqued;

        OauthMember member = OauthMember.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(uuidNickName)
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return oauthMemberRepository.save(member).getId();
    }
}