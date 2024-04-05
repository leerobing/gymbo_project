package com.example.gymbo_back_end.oauth.service;


import com.example.gymbo_back_end.oauth.authApiClient.OAuthApiClient;
import com.example.gymbo_back_end.oauth.dto.authInfoResponse.OAuthInfoResponse;
import com.example.gymbo_back_end.oauth.dto.oAuthLoginParams.OAuthLoginParams;
import com.example.gymbo_back_end.oauth.entity.OAuthProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class RequestOAuthInfoService {
    private final Map<OAuthProvider, OAuthApiClient> clients;

    public RequestOAuthInfoService(List<OAuthApiClient> clients) {
        this.clients = clients.stream().collect(
                Collectors.toUnmodifiableMap(OAuthApiClient::oAuthProvider, Function.identity())
        );
    }

    public OAuthInfoResponse request(OAuthLoginParams params) {
        OAuthApiClient client = clients.get(params.oAuthProvider());
        String accessToken = client.requestAccessToken(params);
        return client.requestOauthInfo(accessToken);
    }
}