package com.webAppCard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = "{com.webAppCard.repository,com.webAppCard.rest,com.webAppCard.service}") 
//@EntityScan("com.webAppCard.model")
public class WebAppCard {
	
	public static void main(String[] args) {
		SpringApplication.run(WebAppCard.class,args);
	}
}
