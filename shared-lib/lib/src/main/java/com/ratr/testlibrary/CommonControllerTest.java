package com.ratr.testlibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommonControllerTest {

    protected WebTestClient testClient;

    @Autowired
    void setMockMvc(MockMvc mockMvc) {
        this.testClient = MockMvcWebTestClient.bindTo(mockMvc).build();
    }
}
