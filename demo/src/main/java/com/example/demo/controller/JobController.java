package com.example.demo.controller;


import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobLauncher jobLauncher;
    private final Job job;

    public JobController(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }


    @PostMapping("/importCustomers")
    public void importCsvToDBJob(){
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();

        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobParametersInvalidException |
                 JobInstanceAlreadyCompleteException | JobRestartException e) {
            e.printStackTrace();
        }
    }
}
