package com.code.truck.sendbookemailsb.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendBookLoanNotificationJobConfig {
    @Bean
    public Job sendBookLoanNotificationJob(final Step sendEmailUserStep, final JobRepository jobRepository) {
        return new JobBuilder("sendBookLoanNotificationJob", jobRepository)
                .start(sendEmailUserStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
