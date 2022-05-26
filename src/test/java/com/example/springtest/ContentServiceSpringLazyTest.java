package com.example.springtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties="spring.main.lazy-initialization=true")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ContentServiceSpringLazyTest {

    @Autowired
    ContentService contentService;

    @Autowired
    ContentRepository contentRepository;

    @Test
    public void test() {
        Content returnContent = contentService.saveNewContent();
        Content dbContent = contentRepository.findById(returnContent.getId()).get();

        Assertions.assertAll("return content == db content",
                () -> Assertions.assertEquals(dbContent.getContent(), returnContent.getContent(), "db content must = return content"),
                () -> Assertions.assertEquals(dbContent.getCreatedAt(), returnContent.getCreatedAt(), "created at must the same"));
    }
}
