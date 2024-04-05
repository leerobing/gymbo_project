package com.example.gymbo_back_end.oauth.dto.oAuthLoginParams;


import com.example.gymbo_back_end.oauth.entity.OAuthProvider;
import org.springframework.util.MultiValueMap;

public interface OAuthLoginParams {
        OAuthProvider oAuthProvider();


        MultiValueMap<String, String> makeBody();
}
