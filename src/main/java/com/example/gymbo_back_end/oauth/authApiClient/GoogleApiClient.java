package com.example.gymbo_back_end.oauth.authApiClient;


import com.example.gymbo_back_end.oauth.dto.authInfoResponse.GoogleInfoResponse;
import com.example.gymbo_back_end.oauth.dto.authInfoResponse.OAuthInfoResponse;
import com.example.gymbo_back_end.oauth.dto.oAuthLoginParams.OAuthLoginParams;
import com.example.gymbo_back_end.oauth.entity.OAuthProvider;
import com.example.gymbo_back_end.oauth.tokens.GoogleTokens;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class GoogleApiClient implements OAuthApiClient{

    private static final String GRANT_TYPE = "authorization_code";

    private final RestTemplate restTemplate;


    @Value("${oauth.google.url.auth}")
    private String authUrl;

    @Value("${oauth.google.url.api}")
    private String apiUrl;

    @Value("${oauth.google.client-id}")
    private String clientId;

    @Value("${oauth.google.secret}")
    private String clientSecret;
    private String redirectUri = "http://localhost:8080/login/oauth2/code/google";
    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.GOOGLE;
    }

    @Override
    public String requestAccessToken(OAuthLoginParams params) {
        String url = authUrl + "/token";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = params.makeBody();
        body.add("grant_type", GRANT_TYPE);
        body.add("client_id", clientId);
        body.add("client_secret",clientSecret);
        body.add("redirect_uri",redirectUri);

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        GoogleTokens response = restTemplate.postForObject(url, request, GoogleTokens.class);

        assert response != null;
        log.info("response : {}",response);
        return response.getAccessToken();
    }

    @Override
    public OAuthInfoResponse requestOauthInfo(String accessToken) {
        String url = apiUrl+ "/userinfo/v2/me";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + accessToken);

//        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        //https://www.googleapis.com/oauth2/v1/userinfo?access_token=${accessToken}

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<GoogleInfoResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, GoogleInfoResponse.class);

        return response.getBody();
    }
}
