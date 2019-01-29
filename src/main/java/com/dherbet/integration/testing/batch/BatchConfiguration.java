package com.dherbet.integration.testing.batch;

import java.util.Map;
import javax.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.JsonLineMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final String inputFile;

    public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
            EntityManagerFactory entityManagerFactory, @Value("${request.file}") String inputFile) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
        this.inputFile = inputFile;
    }

    @Bean
    public RequestInformationProcessor processor() {
        return new RequestInformationProcessor();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Map<String, Object>> reader() {
        FlatFileItemReader<Map<String, Object>> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(inputFile));
        JsonLineMapper jsonLineMapper = new JsonLineMapper();
        reader.setLineMapper(jsonLineMapper);
        return reader;
    }

    @Bean
    public ItemWriter<RequestInformation> writer() {
        JpaItemWriter<RequestInformation> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    @Bean
    public Step processRequestsInformation(ItemWriter<RequestInformation> writer,
            FlatFileItemReader<Map<String, Object>> reader, RequestInformationProcessor processor) {
        return stepBuilderFactory.get("processRequestsInformation")
                .<Map<String, Object>, RequestInformation>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importRequestsInformation(Step processMetricsFile) {
        return jobBuilderFactory.get("importRequestsInformation")
                .start(processMetricsFile)
                .build();
    }

}
