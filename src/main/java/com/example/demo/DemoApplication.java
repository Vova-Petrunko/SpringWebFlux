package com.example.demo;

import com.example.demo.hello.GreetingWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		GreetingWebClient greetingWebClient = new GreetingWebClient();
		System.out.println(greetingWebClient.getClientResponse());
	}
}
