package com.dherbet.integration.testing.batch;

import static org.junit.Assert.assertNotNull;

import java.util.Map;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.JsonLineMapper;
import org.springframework.core.io.ClassPathResource;

public class RequestInformationProcessorTest {

    private static final String REQUEST_INFORMATION_FILE = "batch/test-request.txt";

    private static RequestInformationProcessor processor;

    @BeforeClass
    public static void setUp() {
        processor = new RequestInformationProcessor();
    }

    @Test
    public void processSuccess() throws Exception {
        Map<String, Object> parsedRequestInformationFile = parseRequestInformationFile();
        RequestInformation requestInformation = processor.process(parsedRequestInformationFile);
        assertNotNull(requestInformation);
        assertNotNull(requestInformation.getEndpoint());
        assertNotNull(requestInformation.getIpAddress());
        assertNotNull(requestInformation.getMethod());
        assertNotNull(requestInformation.getTimestamp());
        assertNotNull(requestInformation.getUrl());
    }

    private Map<String, Object> parseRequestInformationFile() throws Exception {
        FlatFileItemReader<Map<String, Object>> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource(REQUEST_INFORMATION_FILE));
        reader.setLineMapper(new JsonLineMapper());
        reader.open(new ExecutionContext());
        Map<String, Object> metric = reader.read();
        reader.close();
        return metric;
    }

}