package com.yarpg.application;

import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.yarpg.rest.user.UserProvider;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.yarpg.application.configuration")
public class Application {

    public static void main(String[] args) {
        ResteasyProviderFactory.getInstance()
                .registerProvider(UserProvider.class);
        SpringApplication.run(Application.class, args);
    }
}
