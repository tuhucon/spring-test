package com.example.springtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
/*
@Service is not created, but @Bean in configuration is created
 */
public class ContentServiceJpaTest {

    @Autowired(required = false)
    ContentRepository contentRepository;

    @Autowired(required = false)
    GoogleService googleService;

    @Autowired(required = false)
    ContentService contentService;

    @Autowired(required = false)
    RestTemplate restTemplate;

    @Autowired(required = false)
    ObjectMapper objectMapper;

    @Test
    public void test1() {
        Assertions.assertAll(() -> Assertions.assertNotNull(contentRepository, "content repository must not null"), //OK
                () -> Assertions.assertNotNull(googleService, "google service must not null"), //NG
                () -> Assertions.assertNotNull(contentService, "content service must not null"), //NG
                () -> Assertions.assertNotNull(restTemplate, "test template must not null"), //OK
                () -> Assertions.assertNotNull(objectMapper, "object mapper must not null")); //NG
    }

    @Test
    public void test2() {
        GoogleService googleService = new GoogleService(restTemplate);
        ContentService contentService = new ContentService(contentRepository, googleService);

        Content returnContent = contentService.saveNewContent();
        Content dbContent = contentRepository.findById(returnContent.getId()).get();

        Assertions.assertAll("return content vs db content",
                () -> Assertions.assertEquals(dbContent.getContent(), returnContent.getContent(), "content must the same"),
                () -> Assertions.assertEquals(dbContent.getCreatedAt(), returnContent.getCreatedAt(), "created at must the same"));


    }
}
