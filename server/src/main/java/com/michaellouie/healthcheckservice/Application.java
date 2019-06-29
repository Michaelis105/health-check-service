package com.michaellouie.healthcheckservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Starting point for initialization
 */
@SpringBootApplication
@EnableScheduling
@EnableCaching
public class Application {

    public static void main(String[] args) { SpringApplication.run(Application.class, args); }
}
