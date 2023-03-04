package com.service.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(
        scanBasePackages = {"com.service.student",
                "com.service.amqp"
        }

)
@EnableEurekaClient
public class StudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class ,args);
    }
}
