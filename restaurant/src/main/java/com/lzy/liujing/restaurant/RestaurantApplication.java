package com.lzy.liujing.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}

	/*@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer(){
		return new EmbeddedServletContainerCustomizer(){
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				//单位s  这里设置30分钟
				container.setSessionTimeout(1800);
			}
		};
	}*/
}
