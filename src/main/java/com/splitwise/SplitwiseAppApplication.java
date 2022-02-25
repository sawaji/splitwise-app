package com.splitwise;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.splitwise")
@EnableJpaRepositories(basePackages = "com.splitwise")
@EnableConfigurationProperties
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class SplitwiseAppApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(SplitwiseAppApplication.class, args);
	}


}

//POST :http://localhost:8080/api/auth/signup
//Request
/*
{
		"username": "mod",
		"email": "sawaji.varun@gmail.com",
		"password": "12345678",
		"role": [
		"user",
		"mod"
		]
		}
*/// Here user name is mod and role user

//GET : http://localhost:8080/api/test/all

//post : http://localhost:8080/api/auth/signin

//request :
/*{
		"username": "mod",
		"password": "12345678"
		}*/

/*
output:
		{
		"refreshToken": "a6d658d7-a74e-4fec-922b-5c2e522c6b9e",
		"id": 1,
		"username": "mod",
		"email": "sawaji.varun@gmail.com",
		"roles": [
		"ROLE_MODERATOR",
		"ROLE_USER"
		],
		"tokenType": "Bearer",
		"accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtb2QiLCJpYXQiOjE2NDU1NDQ2ODUsImV4cCI6MTY0NTU0NDc0NX0.QZU80dHm9cjxedq7d3Me_g2dEO9XnYeNKI-rWN-6MOe8QhZnWlr1KiJBsoa2Yp1IayZGMJRhGM2yXc__b0MSjw"
		}
*/



//
