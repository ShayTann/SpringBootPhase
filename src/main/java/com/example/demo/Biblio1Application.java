package com.example.demo;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableElasticsearchRepositories("com.example.elastic.repository")
@EnableJpaRepositories("com.example.repository")
@SpringBootApplication
@ComponentScan({"com.example.security","com.example.controller"})
@EntityScan(basePackages = "com.example.model")

public class Biblio1Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Biblio1Application.class, args);
	}
	

}
