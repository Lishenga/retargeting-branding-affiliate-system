package com.retargeting_branding;

import com.retargeting_branding.configs.ApplicationConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
@EnableEurekaClient
public class RetargetingBrandingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetargetingBrandingApplication.class, args);
	}
}
