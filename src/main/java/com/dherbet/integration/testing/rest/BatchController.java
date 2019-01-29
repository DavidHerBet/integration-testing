package com.dherbet.integration.testing.rest;

import com.dherbet.integration.testing.utils.ApplicationUtils;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class BatchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchController.class);

    private final JobLauncher jobLauncher;
    private final Job job;

    public BatchController(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @GetMapping("/launch-batch")
    public String launchBatch(HttpServletRequest request) {
        LOGGER.info("Received REST call to /api/v1/launch-batch");
        ApplicationUtils.logRequestInformation(request);
        try {
            JobExecution jobExecution = jobLauncher.run(job,
                    new JobParametersBuilder().addDate("launchDate", new Date()).toJobParameters());
            return jobExecution.getStatus().toString();
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException e) {
            LOGGER.error("There was an error launching batch", e);
            return BatchStatus.FAILED.toString();
        }
    }


}
