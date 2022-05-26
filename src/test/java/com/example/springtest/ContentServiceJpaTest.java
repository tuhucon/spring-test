package com.example.springtest;

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
public class ContentServiceJpaTest {

    @Autowired(required = false)
    ContentRepository contentRepository;

    @Autowired(required = false)
    GoogleService googleService;

    @Autowired(required = false)
    ContentService contentService;

    @Autowired(required = false)
    RestTemplate restTemplate;

    @Test
    public void test1() {
        Assertions.assertAll(() -> Assertions.assertNotNull(contentRepository),
                () -> Assertions.assertNotNull(googleService),
                () -> Assertions.assertNotNull(contentService),
                () -> Assertions.assertNotNull(restTemplate));
    }
}
