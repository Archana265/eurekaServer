package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@SpringBootApplication
@EnableEurekaServer
@EnableHystrixDashboard
@EnableHystrix
@EnableCircuitBreaker
@RestController
@EnableTurbine
public class EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}
	
	@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.GET)
	@HystrixCommand(groupKey = "notification-service", fallbackMethod = "statusPageDown")
	public ResponseEntity hytrixTest() {
	    throw new RuntimeException("Simulating downstream system failure");
	}
	 public ResponseEntity statusPageDown() {
	        return new ResponseEntity(HttpStatus.OK);
	    }
	
}
