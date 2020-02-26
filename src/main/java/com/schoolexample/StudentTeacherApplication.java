package com.schoolexample;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@Service
@SpringBootApplication
public class StudentTeacherApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentTeacherApplication.class, args);
    }

}
