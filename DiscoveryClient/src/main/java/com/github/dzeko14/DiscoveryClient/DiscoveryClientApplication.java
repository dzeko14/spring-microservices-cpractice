package com.github.dzeko14.DiscoveryClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class DiscoveryClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryClientApplication.class, args);
	}

}
