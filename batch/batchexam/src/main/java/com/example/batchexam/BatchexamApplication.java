package com.example.batchexam;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class BatchexamApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchexamApplication.class, args);
    }

}
