package com.example.amazonreplica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AmazonreplicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmazonreplicaApplication.class, args);
	}

	
	@Bean
	public WebMvcConfigurer crossConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// TODO Auto-generated method stub
				registry.addMapping("/**").allowedOrigins("http://127.0.0.1:5500/");
			}
		};
	}
}
