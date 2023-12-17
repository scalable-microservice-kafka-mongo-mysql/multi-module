package com.ashutoshpathak.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    //Spring beans have same name as the methodName instantiating them.
    //So if i @Autowire somewhere .... , that object's name i'll have to make as webClient itself!
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}
