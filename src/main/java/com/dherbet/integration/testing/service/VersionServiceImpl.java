package com.dherbet.integration.testing.service;


import com.dherbet.integration.testing.domain.Version;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VersionServiceImpl implements VersionService {

    private final Version version;

    public VersionServiceImpl(@Value("${version.application}") String applicationVersion,
            @Value("${version.spring-boot}") String springBootVersion) {
        this.version = new Version();
        version.setApplicationVersion(applicationVersion);
        version.setSpringBootVersion(springBootVersion);
    }

    @Override
    public Version getVersions() {
        return version;
    }
}
