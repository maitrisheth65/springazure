package com.maitri.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EntityScan("com.maitri.model")
@ComponentScan("com.maitri")
@EnableJpaRepositories("com.maitri.dao")
@SpringBootApplication
public class JwtApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}

}
