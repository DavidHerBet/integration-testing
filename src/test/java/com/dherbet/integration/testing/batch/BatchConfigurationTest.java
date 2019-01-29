package com.dherbet.integration.testing.batch;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManagerFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.builder.TaskletStepBuilder;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemProcessor;

public class BatchConfigurationTest {

    private static BatchConfiguration batchConfiguration;

    @BeforeClass
    public static void setUp() {
        JobBuilderFactory jobBuilderFactory = mockJobBuilderFactory();
        StepBuilderFactory stepBuilderFactory = mockStepBuilderFactory();
        EntityManagerFactory entityManagerFactory = mock(EntityManagerFactory.class);
        String inputFile = "src/test/resources/batch/test-request.log";
        batchConfiguration = new BatchConfiguration(jobBuilderFactory, stepBuilderFactory, entityManagerFactory,
                inputFile);
    }

    @Test
    public void processor() {
        assertNotNull(batchConfiguration.processRequestsInformation(
                batchConfiguration.writer(),
                batchConfiguration.reader(),
                batchConfiguration.processor()
        ));
    }

    @Test
    public void reader() {
        assertNotNull(batchConfiguration.reader());
    }

    @Test
    public void writer() {
        assertNotNull(batchConfiguration.writer());
    }

    @Test
    public void processRequestsInformation() {
        assertNotNull(batchConfiguration.processRequestsInformation(
                batchConfiguration.writer(),
                batchConfiguration.reader(),
                batchConfiguration.processor()
        ));
    }

    @Test
    public void importRequestsInformation() {
        assertNotNull(batchConfiguration.importRequestsInformation(
                batchConfiguration.processRequestsInformation(
                        batchConfiguration.writer(),
                        batchConfiguration.reader(),
                        batchConfiguration.processor()
                )
        ));
    }



    private static JobBuilderFactory mockJobBuilderFactory() {
        JobBuilderFactory jobBuilderFactory = mock(JobBuilderFactory.class);
        JobBuilder jobBuilder = mock(JobBuilder.class);
        SimpleJobBuilder simpleJobBuilder = mock(SimpleJobBuilder.class);
        when(jobBuilderFactory.get(anyString())).thenReturn(jobBuilder);
        when(jobBuilder.start(any(Step.class))).thenReturn(simpleJobBuilder);
        when(simpleJobBuilder.build()).thenReturn(mock(Job.class));
        return jobBuilderFactory;
    }

    private static StepBuilderFactory mockStepBuilderFactory() {
        StepBuilderFactory stepBuilderFactory = mock(StepBuilderFactory.class);
        StepBuilder stepBuilder = mock(StepBuilder.class);
        SimpleStepBuilder simpleStepBuilder = mock(SimpleStepBuilder.class);
        TaskletStepBuilder taskletStepBuilder = mock(TaskletStepBuilder.class);
        when(stepBuilder.chunk(anyInt())).thenReturn(simpleStepBuilder);
        when(stepBuilder.tasklet(any())).thenReturn(taskletStepBuilder);
        when(taskletStepBuilder.build()).thenReturn(new TaskletStep());
        when(simpleStepBuilder.reader(any())).thenReturn(simpleStepBuilder);
        when(simpleStepBuilder.processor(any(ItemProcessor.class))).thenReturn(simpleStepBuilder);
        when(simpleStepBuilder.writer(any())).thenReturn(simpleStepBuilder);
        when(simpleStepBuilder.build()).thenReturn(new TaskletStep());
        when(stepBuilderFactory.get(anyString())).thenReturn(stepBuilder);
        return stepBuilderFactory;
    }

}