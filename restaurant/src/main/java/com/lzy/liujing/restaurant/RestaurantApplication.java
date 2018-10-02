package com.lzy.liujing.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lzy.liujing.restaurant")
public class RestaurantApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}
}
