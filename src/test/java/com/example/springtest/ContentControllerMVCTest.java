package com.example.springtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ContentController.class)
@MockBean(JpaMetamodelMappingContext.class)
/*
Using this annotation will disable full auto-configuration and instead apply only configuration relevant to MVC tests
(i.e. @Controller, @ControllerAdvice, @JsonComponent, Converter/GenericConverter, Filter, WebMvcConfigurer and
HandlerMethodArgumentResolver beans but not @Component, @Service or @Repository beans).
 */
public class ContentControllerMVCTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ContentService contentService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void test() throws Exception {
        Content content = new Content();
        content.setContent("content");
        content.setId(1L);
        content.setCreatedAt(new Date());
        Mockito.when(contentService.saveNewContent()).thenReturn(content);

        String response = mockMvc.perform(MockMvcRequestBuilders.get("/newContent"))
                .andReturn().getResponse().getContentAsString();
        Content res = objectMapper.readValue(response, Content.class);

        Assertions.assertAll("content equal",
                () -> Assertions.assertEquals(content.getId(), res.getId()),
                () -> Assertions.assertEquals(content.getContent(), res.getContent()),
                () -> Assertions.assertEquals(content.getCreatedAt(), res.getCreatedAt()));
    }

}
