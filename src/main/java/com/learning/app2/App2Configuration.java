package com.learning.app2;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class App2Configuration {

	@Bean
	public RestTemplate restTemplateConfig() {

		return new RestTemplate();
	}
}
