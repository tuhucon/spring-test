package com.example.springtest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ContentController.class)
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

    @Test
    public void test() {

    }

}
