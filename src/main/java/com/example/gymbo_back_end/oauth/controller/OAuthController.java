package com.example.gymbo_back_end.oauth.controller;


import com.example.gymbo_back_end.oauth.dto.oAuthLoginParams.GoogleLoginParams;
import com.example.gymbo_back_end.oauth.dto.oAuthLoginParams.KakaoLoginParams;
import com.example.gymbo_back_end.oauth.dto.oAuthLoginParams.NaverLoginParams;
import com.example.gymbo_back_end.oauth.jwt.AuthTokens;
import com.example.gymbo_back_end.oauth.service.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/auth")
public class OAuthController {

    private final OAuthLoginService oAuthLoginService;

    private static final String GRANT_TYPE = "authorization_code";

    private String authUrl = "https://kauth.kakao.com/oauth/token";



    @Value("${oauth.kakao.url.api}")
    private String apiUrl;

    @Value("${oauth.kakao.client-id}")
    private String clientId;


    @PostMapping("/kakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

    @PostMapping("/naver")
    public ResponseEntity<AuthTokens> loginNaver(@RequestBody NaverLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

    @PostMapping("/google")
    public ResponseEntity<AuthTokens> loginGoogle(@RequestBody GoogleLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }
}
