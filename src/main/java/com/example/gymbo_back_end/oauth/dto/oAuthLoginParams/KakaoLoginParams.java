package com.example.gymbo_back_end.oauth.dto.oAuthLoginParams;


import com.example.gymbo_back_end.oauth.entity.OAuthProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
public class KakaoLoginParams implements OAuthLoginParams {

    private String authorizationCode;
   // private String client_secret;

    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.KAKAO;
    }



    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", authorizationCode);
       // body.add("client_secret",client_secret);
        return body;
    }


}