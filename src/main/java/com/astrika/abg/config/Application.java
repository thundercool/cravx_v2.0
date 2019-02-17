package com.astrika.abg.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.astrika.abg.model.User;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "com.astrika.abg.repository.**" })
@EntityScan(basePackages = { "com.astrika.abg.model.**" })
@ComponentScan(basePackages = { "com.astrika.abg.**" })
public class Application extends SpringBootServletInitializer {

	public static void main(String args[]) {
		System.out.println("Test SuccessFull");
		SpringApplication.run(Application.class);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	@Bean
	AuditorAware<User> auditorProvider() {
		return new AuditwareConfig();
	}

}
