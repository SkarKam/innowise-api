package com.innowise.kaminski.innowiseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class InnowiseApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(InnowiseApiApplication.class, args);
    }

}
