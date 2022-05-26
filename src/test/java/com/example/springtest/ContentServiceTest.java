package com.example.springtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class ContentServiceTest {

    @Mock
    ContentRepository contentRepository;

    @Mock
    GoogleService googleService;

    @Test
    public void test1() {
        Date createdAt = new Date();
        Mockito.when(googleService.requestGoogle()).thenReturn("Tu hu con");
        Mockito.when(contentRepository.save(Mockito.any(Content.class))).thenAnswer(context -> {
            Content content = context.getArgument(0);
            content.setId(1L);
            content.setCreatedAt(createdAt);
            return content;
        });

        ContentService contentService = new ContentService(contentRepository, googleService);

        Content content = contentService.saveNewContent();
        Assertions.assertAll("content", () -> Assertions.assertEquals(1L, content.getId()),
                () -> Assertions.assertEquals("Tu hu con", content.getContent()),
                () -> Assertions.assertEquals(createdAt, content.getCreatedAt()));
    }

}
