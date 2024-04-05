package com.example.gymbo_back_end.oauth.dto.oAuthLoginParams;

import com.example.gymbo_back_end.oauth.entity.OAuthProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Getter
@NoArgsConstructor
public class GoogleLoginParams implements OAuthLoginParams{

    private String authorizationCode;
    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.GOOGLE;
    }

    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        String decodeAuthorizationCode = URLDecoder.decode(authorizationCode, StandardCharsets.UTF_8);
        body.add("code", decodeAuthorizationCode);
        return body;
    }
}
