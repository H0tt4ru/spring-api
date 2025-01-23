package com.example.spring_api.init;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.example.base_domain.dto.model")
@ComponentScan(basePackages = {
        "com.example.service_history",
        "com.example.service_student",
        "com.example.service_auth",
        "com.example.service_transaction"
})
@EnableJpaRepositories(basePackages = "com.example.base_domain.repository")
public class init {

}
