package com.example.springtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({GoogleService.class, ContentService.class})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ContentServiceJpaImportOtherServiceTest {

    @Autowired
    GoogleService googleService;

    @Autowired
    ContentService contentService;

    @Test
    public void test() {
        Content returnContent = contentService.saveNewContent();
        Assertions.assertEquals(1L, returnContent.getId());
    }
}
