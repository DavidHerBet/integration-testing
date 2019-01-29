package com.dherbet.integration.testing.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.dherbet.integration.testing.batch.RequestInformation;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
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
public class BatchControllerIT {

    private static final String DATABASE_QUERY = "SELECT * FROM REQUEST_INFORMATION";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void launchBatch() {
        // Test database is empty
        Query query = entityManager.createNativeQuery(DATABASE_QUERY, RequestInformation.class);
        List<RequestInformation> requestInformations = (List<RequestInformation>) query.getResultList();
        assertTrue(requestInformations.isEmpty());

        // Execute batch
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/v1/launch-batch", String.class);
        String batchStatus = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(BatchStatus.COMPLETED.toString(), batchStatus);

        // Test database contains information
        query = entityManager.createNativeQuery(DATABASE_QUERY, RequestInformation.class);
        requestInformations = (List<RequestInformation>) query.getResultList();
        assertTrue(!requestInformations.isEmpty());
        RequestInformation requestInformation = requestInformations.get(0);
        assertEquals(new Date(1548688552301L), requestInformation.getTimestamp());
        assertEquals("0:0:0:0:0:0:0:1", requestInformation.getIpAddress());
        assertEquals("/api/v1/version", requestInformation.getEndpoint());
        assertEquals("http://localhost:8070/api/v1/version", requestInformation.getUrl());
        assertEquals("GET", requestInformation.getMethod());
    }

}