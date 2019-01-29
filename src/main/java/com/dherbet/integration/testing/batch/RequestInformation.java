package com.dherbet.integration.testing.batch;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RequestInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQUEST_INFORMATION_ID")
    private Long id;

    private Date timestamp;
    private String ipAddress;
    private String endpoint;
    private String url;
    private String method;

    public RequestInformation() {
        // Explicit default constructor
    }

    public Date getTimestamp() {
        return (Date) timestamp.clone();
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = (Date) timestamp.clone();
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

}
