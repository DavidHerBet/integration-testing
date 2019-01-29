package com.dherbet.integration.testing.rest;

import static org.junit.Assert.assertEquals;

import com.dherbet.integration.testing.domain.Version;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ApplicationControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void contextLoads() {
        //Dummy test to see Spring Loads context and application is started
    }

    @Test
    public void hello() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/v1/hello", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("hello", response.getBody());
    }

    @Test
    public void version() {
        ResponseEntity<Version> response = testRestTemplate.getForEntity("/api/v1/version", Version.class);
        Version version = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("2-TEST", version.getSpringBootVersion());
        assertEquals("1-TEST", version.getApplicationVersion());
    }

}