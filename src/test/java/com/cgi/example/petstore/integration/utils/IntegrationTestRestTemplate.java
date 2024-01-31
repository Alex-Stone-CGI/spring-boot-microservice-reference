package com.cgi.example.petstore.integration.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class IntegrationTestRestTemplate {

    private static final Logger LOG = LoggerFactory.getLogger(IntegrationTestRestTemplate.class);

    private final TestRestTemplate testRestTemplate;

    public IntegrationTestRestTemplate(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    public ResponseEntity<String> execute(RequestEntity<String> requestEntity) {
        ResponseEntity<String> response = testRestTemplate.exchange(requestEntity, String.class);
        LOG.info("Integration test ResponseEntity: [{}]", response);
        return response;

    }
}