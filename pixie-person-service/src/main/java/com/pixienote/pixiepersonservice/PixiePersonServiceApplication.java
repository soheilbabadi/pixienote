package com.pixienote.pixiepersonservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PixiePersonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PixiePersonServiceApplication.class, args);
    }

}
