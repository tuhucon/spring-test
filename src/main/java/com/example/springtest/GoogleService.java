package com.example.springtest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GoogleService {

    private final RestTemplate restTemplate;

    public String requestGoogle() {
        return restTemplate.getForEntity("https://www.google.com.vn/", String.class).getBody();
    }
}
