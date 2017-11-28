package com.github.zf.eureka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author zengfen
 * @date 2017/11/28
 */
@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEurekaApplication {

    private final static Logger logger = LoggerFactory.getLogger(SpringCloudEurekaApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(SpringCloudEurekaApplication.class, args);
        logger.info("------------------------------------------");
        logger.info(" SpringCloudEurekaApplication start");
        logger.info("------------------------------------------");
    }
}
