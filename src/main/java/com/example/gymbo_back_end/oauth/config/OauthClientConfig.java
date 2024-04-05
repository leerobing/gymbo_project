package com.example.gymbo_back_end.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OauthClientConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}