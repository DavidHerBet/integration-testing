package com.dherbet.integration.testing.utils;

import com.dherbet.integration.testing.domain.RequestInformation;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

public final class ApplicationUtils {

    private ApplicationUtils(){
        throw new AssertionError();
    }

    public static void logRequestInformation(HttpServletRequest request) {
        RequestInformation requestInformation = new RequestInformation();
        requestInformation.setTimestamp(new Date().getTime());
        requestInformation.setEndpoint(request.getRequestURI());
        requestInformation.setIpAddress(request.getRemoteAddr());
        requestInformation.setUrl(request.getRequestURL().toString());
        requestInformation.setMethod(request.getMethod());
        requestInformation.log();
    }

}
