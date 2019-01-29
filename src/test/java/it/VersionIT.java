package it;

import static org.junit.Assert.assertEquals;

import com.dherbet.integration.testing.domain.Version;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Ignore
public class VersionIT {

    private static String TEST_URL = "http://localhost:8070/api/v1/version";
    private static RestTemplate restTemplate = new RestTemplate();

    @Test
    public void version() {
        ResponseEntity<Version> response = restTemplate.getForEntity(TEST_URL, Version.class);
        Version version = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("2.1.2.RELEASE", version.getSpringBootVersion());
        assertEquals("1.0.0-SNAPSHOT", version.getApplicationVersion());
    }

}
