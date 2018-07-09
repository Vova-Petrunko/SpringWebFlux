package com.example.demo.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingRouterTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void route() {
        webTestClient
                .get().uri("/hello")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello, Spring");
    }

    @Test
    public void responseRouterFunction() {
        webTestClient
                .get().uri("/example1")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello, example 1!");

        webTestClient
                .get().uri("/example2")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello, example 2!");
    }

    @Test
    public void responseExample3WithParameter() {
        webTestClient
                .get().uri("/example3?name=Volodymyr Petrunko")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello Volodymyr Petrunko !");
    }
}