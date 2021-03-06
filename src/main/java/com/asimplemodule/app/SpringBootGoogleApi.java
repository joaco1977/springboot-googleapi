package com.asimplemodule.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.asimplemodule.controller",
		"com.asimplemodule.config",
		"com.asimplemodule.security"})
@RestController
public class SpringBootGoogleApi {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGoogleApi.class, args);
    }
    
    
    @RequestMapping( value="/",method=RequestMethod.GET)
	public String home() {
		return "Hello";
	}
    
}
