package com.example.demo;

import com.example.demo.hello.webClient.BookWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        BookWebClient bookWebClient = new BookWebClient();
        bookWebClient.doStuff();
    }
}
