package com.yanyi.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String [] args) {

        LOGGER.info("Start yanyi dictionary system...");
        LOGGER.info("Begin to startup restful service ...");
        SpringApplication application;
        try {
            application = new SpringApplication("classpath:spring/application.xml");
            application.run();
            LOGGER.info("Startup restful service success ...");
        } catch (Exception e) {
            LOGGER.error("Startup spring application failed", e);
            System.exit(1);
        }
    }
}
