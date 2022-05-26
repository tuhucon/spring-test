package com.example.springtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(properties="spring.main.lazy-initialization=true", webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ContentControllerSpringTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void test() {
        Content content = testRestTemplate.getForEntity("/newContent", Content.class).getBody();

        Assertions.assertEquals(1L, content.getId(), "id must be 1L");
    }
}
