package com.webAppCard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "{com.webAppCard.model,com.webAppCard.repository,com.webAppCard.rest,com.webAppCard.service}") 
public class WebAppCard {
	
	public static void main(String[] args) {
		SpringApplication.run(WebAppCard.class,args);
	}
}
