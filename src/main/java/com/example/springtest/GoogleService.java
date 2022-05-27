package com.example.springtest;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleService {

    private final RestTemplate restTemplate;

    public GoogleService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String requestGoogle() {
        return restTemplate.getForEntity("https://www.google.com.vn/", String.class).getBody();
    }
}
