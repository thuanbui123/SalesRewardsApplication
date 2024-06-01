package com.example;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@SpringBootApplication
public class SalesRewardsApplication {

    public static void main(String[] args) {
        try {
            Resource log4jConfigFile = new ClassPathResource("log4j.properties");
            PropertyConfigurator.configure(log4jConfigFile.getURL());
        } catch (IOException e) {
            System.err.println("Error configuring Log4j: " + e.getMessage());
        }
        SpringApplication.run(SalesRewardsApplication.class, args);
    }

}
