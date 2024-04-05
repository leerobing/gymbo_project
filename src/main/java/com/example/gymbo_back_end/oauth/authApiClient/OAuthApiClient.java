package com.example.gymbo_back_end.oauth.authApiClient;


import com.example.gymbo_back_end.oauth.dto.authInfoResponse.OAuthInfoResponse;
import com.example.gymbo_back_end.oauth.dto.oAuthLoginParams.OAuthLoginParams;
import com.example.gymbo_back_end.oauth.entity.OAuthProvider;

public interface OAuthApiClient {
    OAuthProvider oAuthProvider();
    String requestAccessToken(OAuthLoginParams params);
    OAuthInfoResponse requestOauthInfo(String accessToken);
}