package com.shanhh.genie.cherrynic.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author shanhonghao
 * @since 2018-04-14 14:57
 */
@SpringBootApplication(scanBasePackages = "com.shanhh")
@EnableJpaRepositories("com.shanhh")
@EntityScan("com.shanhh")
@EnableFeignClients
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
