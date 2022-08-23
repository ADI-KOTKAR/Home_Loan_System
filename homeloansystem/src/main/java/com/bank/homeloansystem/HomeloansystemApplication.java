package com.bank.homeloansystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HomeloansystemApplication {

	public static void main(String[] args) {
//		SpringApplication.run(HomeloansystemApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(HomeloansystemApplication.class, args);
        // do something
		System.out.print("Everything's Working fine!!!");
        context.close();
	}

}
