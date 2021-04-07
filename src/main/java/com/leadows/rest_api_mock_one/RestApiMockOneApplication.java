package com.leadows.rest_api_mock_one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 

@SpringBootApplication
public class RestApiMockOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiMockOneApplication.class, args);
	}

}

@RestController
class HelloController {
 
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World RESTful with Spring Boot";
    }  
}