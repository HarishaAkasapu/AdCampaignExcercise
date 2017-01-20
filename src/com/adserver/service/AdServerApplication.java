package com.adserver.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Harisha Akasapu 
 * Using spring boot application to quickly run standalone Spring application and make respective REST API calls.
 */
@SpringBootApplication
public class AdServerApplication {
	
	/**
	 * Main method for further delegation and bootstrap the application. 
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AdServerApplication.class, args);

		/* Invoke sample REST client for testing */
		CampaignClient client = (CampaignClient) context.getBean("campaignClient");
		client.testAdCampaigns();
	}

	@Bean
	public RestTemplate geRestTemplate() {
		return new RestTemplate();
	}
}
