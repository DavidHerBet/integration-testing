package com.dherbet.integration.testing.batch;

import java.util.Date;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * Provides processing of the metrics file.
 */
public class RequestInformationProcessor implements ItemProcessor<Map<String, Object>, RequestInformation> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestInformationProcessor.class);

    @Override
    public RequestInformation process(Map<String, Object> request) {
        RequestInformation requestInfo = new RequestInformation();
        requestInfo.setEndpoint((String) request.get("endpoint"));
        requestInfo.setIpAddress((String) request.get("ipAddress"));
        requestInfo.setMethod((String) request.get("method"));
        requestInfo.setUrl((String) request.get("url"));
        requestInfo.setTimestamp(new Date(getLong(request.get("timestamp"))));

        LOGGER.info("Converting ({}) into ({})", request, requestInfo);
        return requestInfo;
    }

    private Long getLong(Object object) {
        Long longValue;
        if (object instanceof Integer) {
            longValue = ((Integer) object).longValue();
        } else {
            longValue = (Long) object;
        }
        return longValue;
    }

}
