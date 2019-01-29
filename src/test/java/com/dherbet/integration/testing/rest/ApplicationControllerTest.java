package com.dherbet.integration.testing.rest;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.dherbet.integration.testing.domain.Version;
import com.dherbet.integration.testing.service.VersionService;
import com.dherbet.integration.testing.service.VersionServiceImpl;
import javax.servlet.http.HttpServletRequest;
import org.junit.BeforeClass;
import org.junit.Test;

public class ApplicationControllerTest {

    private static ApplicationController applicationController;

    @BeforeClass
    public static void setUp() {
        VersionService versionService = new VersionServiceImpl("1.TEST", "1.0.TEST");
        applicationController = new ApplicationController(versionService);
    }

    @Test
    public void helloEndpointSuccess() {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getRequestURL()).thenReturn(new StringBuffer());
        assertEquals("hello",  applicationController.hello(httpServletRequest));
    }

    @Test
    public void versionEndpointSuccess() {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getRequestURL()).thenReturn(new StringBuffer());
        Version version = applicationController.version(httpServletRequest);
        assertEquals("1.TEST",  version.getApplicationVersion());
        assertEquals("1.0.TEST",  version.getSpringBootVersion());
    }

}