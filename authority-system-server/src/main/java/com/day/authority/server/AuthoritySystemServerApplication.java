package com.day.authority.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author DayXhYao
 * @date 2023/3/12 13:21
 */
@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = "com.day.authority")
public class AuthoritySystemServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(AuthoritySystemServerApplication.class, args);
    }

}
