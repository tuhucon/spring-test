package com.example.springtest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    private final GoogleService googleService;

    @Transactional
    public Content saveNewContent() {
        String content = googleService.requestGoogle();
        Content contentEntity = new Content();
        contentEntity.setContent(content);
        return contentRepository.save(contentEntity);
    }
}
