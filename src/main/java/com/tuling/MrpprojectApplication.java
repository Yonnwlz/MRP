package com.tuling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MrpprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MrpprojectApplication.class, args);
    }

}
