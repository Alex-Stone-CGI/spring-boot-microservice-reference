package com.cgi.example.petstore.integration;

import com.cgi.example.petstore.integration.utils.LoggingTestRestTemplate;
import com.cgi.example.petstore.integration.utils.UriBuilder;
import com.cgi.example.petstore.integration.utils.WiremockServerForIntegrationTests;
import com.cgi.example.petstore.utils.AssertionUtils;
import com.cgi.example.petstore.utils.ResourceFileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"management.server.port=0",
                "de.flapdoodle.mongodb.embedded.version=4.4.18"})
@Tag("integration")
public class BaseIntegrationTest {

    protected final AssertionUtils assertions = new AssertionUtils();
    protected final ResourceFileUtils fileUtils = new ResourceFileUtils();

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    protected LoggingTestRestTemplate testRestTemplate;

    @Autowired
    protected UriBuilder uriBuilder;

    @Autowired
    protected WiremockServerForIntegrationTests stubServer;

    private void dropAllMongoDBCollections() {
        Set<String> collectionNames = mongoTemplate.getCollectionNames();
        collectionNames.forEach(mongoTemplate::dropCollection);
    }

    @BeforeEach
    void beforeEach() {
        stubServer.resetAll();
        dropAllMongoDBCollections();
    }

    @AfterEach
    void afterEach() {
        stubServer.resetAll();
        dropAllMongoDBCollections();
    }
}