package com.mastercard.billingsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@EntityScan("com.mastercard.billingsearch.entity")
//@EnableJpaRepositories("com.mastercard.billingsearch.repository")

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public RestTemplate getRestTemplate() {
       return new RestTemplate();
    }
    
}
