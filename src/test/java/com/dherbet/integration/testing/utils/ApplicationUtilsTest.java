package com.dherbet.integration.testing.utils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import org.junit.Test;

public class ApplicationUtilsTest {

    @Test
    public void logRequestInformation() {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        when(httpServletRequest.getRequestURL()).thenReturn(new StringBuffer());
        ApplicationUtils.logRequestInformation(httpServletRequest);

        verify(httpServletRequest).getRequestURI();
        verify(httpServletRequest).getRemoteAddr();
        verify(httpServletRequest).getRequestURL();
        verify(httpServletRequest).getMethod();
    }
}