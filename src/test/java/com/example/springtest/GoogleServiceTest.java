package com.example.springtest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

@ExtendWith(SpringExtension.class)
@RestClientTest(GoogleService.class)
@MockBean(JpaMetamodelMappingContext.class)
public class GoogleServiceTest {

    @Autowired
    GoogleService googleService;

    @Autowired
    RestTemplate restTemplate;

    MockRestServiceServer mockRestServiceServer;

    @BeforeAll
    public void setup() {
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void test1() {
        mockRestServiceServer.expect(MockRestRequestMatchers.requestTo("https://www.google.com.vn/"))
                .andRespond(MockRestResponseCreators.withSuccess("Tu hu con", MediaType.TEXT_HTML));

        String str = googleService.requestGoogle();
        Assertions.assertEquals("Tu hu con", str);
    }

}