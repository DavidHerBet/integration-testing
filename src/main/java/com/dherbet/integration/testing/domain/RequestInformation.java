package com.dherbet.integration.testing.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestInformation {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestInformation.class);
    private static final ObjectMapper JACKSON_MAPPER = new ObjectMapper();

    private long timestamp;
    private String ipAddress;
    private String endpoint;
    private String url;
    private String method;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void log() {
        try {
            String json = JACKSON_MAPPER.writeValueAsString(this);
            LOGGER.info(json);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error transforming to JSON", e);
        }
    }
}
