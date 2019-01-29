package com.dherbet.integration.testing.service;

import static org.junit.Assert.assertEquals;

import com.dherbet.integration.testing.domain.Version;
import org.junit.BeforeClass;
import org.junit.Test;

public class VersionServiceImplTest {

    private static VersionService versionService;

    @BeforeClass
    public static void setUp() {
        versionService = new VersionServiceImpl("1.TEST", "1.0.TEST");
    }

    @Test
    public void getVersionsSuccess(){
        Version version = versionService.getVersions();
        assertEquals("1.TEST",  version.getApplicationVersion());
        assertEquals("1.0.TEST",  version.getSpringBootVersion());
    }

}