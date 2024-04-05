package com.example.gymbo_back_end.oauth.dto.authInfoResponse;


import com.example.gymbo_back_end.oauth.entity.OAuthProvider;

public interface OAuthInfoResponse {
    String getEmail();
    String getNickname();
    OAuthProvider getOAuthProvider();
}
