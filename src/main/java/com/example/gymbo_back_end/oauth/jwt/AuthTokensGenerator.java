package com.example.gymbo_back_end.oauth.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class AuthTokensGenerator {
    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일

    private final OauthJwtTokenProvider oauthJwtTokenProvider;

    public AuthTokens generate(Long memberId) {
        long now = (new Date()).getTime();
        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        String subject = memberId.toString();
        String accessToken = oauthJwtTokenProvider.generate(subject, accessTokenExpiredAt);
        String refreshToken = oauthJwtTokenProvider.generate(subject, refreshTokenExpiredAt);

        return AuthTokens.of(accessToken, refreshToken, BEARER_TYPE, ACCESS_TOKEN_EXPIRE_TIME / 1000L);
    }

    public Long extractMemberId(String accessToken) {
        return Long.valueOf(oauthJwtTokenProvider.extractSubject(accessToken));
    }
}