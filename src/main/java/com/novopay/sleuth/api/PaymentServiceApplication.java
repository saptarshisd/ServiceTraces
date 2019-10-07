package com.novopay.sleuth.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class PaymentServiceApplication {
	private static final Logger log = LoggerFactory.getLogger(PaymentServiceApplication.class);
	
	//injecting bean rest template
	@Autowired
	private RestTemplate template;
	
	@GetMapping("/getDiscount")
	public String discount() {
		log.info("discount service called....");
		return "added discount 15%";
	}
	
	//inside payment we are calling the discount method as a rest client
	@GetMapping("/payment")
	public String payment() {
		log.info("payment service called with discount...");
		return template.getForObject("http://localhost:8080/getDiscount", String.class); 
	}

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}
 
}


//trace id should be same across microservices where as spanId would vary specific to the microservices

// on calling /payment, we are internally calling discount, so we shall get 3 log statements

 