package com.dherbet.integration.testing.rest;

import com.dherbet.integration.testing.domain.Version;
import com.dherbet.integration.testing.service.VersionService;
import com.dherbet.integration.testing.utils.ApplicationUtils;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class ApplicationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    private final VersionService versionService;

    public ApplicationController(VersionService versionService) {
        this.versionService = versionService;
    }

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        LOGGER.info("Received REST call to /api/v1/hello");
        ApplicationUtils.logRequestInformation(request);
        return "hello";
    }

    @GetMapping("/version")
    public Version version(HttpServletRequest request) {
        LOGGER.info("Received REST call to /api/v1/version");
        ApplicationUtils.logRequestInformation(request);
        return versionService.getVersions();
    }


}
