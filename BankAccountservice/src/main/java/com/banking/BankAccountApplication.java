package com.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableDiscoveryClient
@SpringBootApplication
public class BankAccountApplication 
{
	@Bean
	@LoadBalanced
	public RestTemplate getrestTemplate()
	{
		return new RestTemplate();
	}
	public static void main(String[] args) 
	{
		SpringApplication.run(BankAccountApplication.class, args);
	}

}


