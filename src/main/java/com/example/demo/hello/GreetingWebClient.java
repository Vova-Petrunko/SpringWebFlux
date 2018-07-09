package com.example.demo.hello;


import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class GreetingWebClient {
    private WebClient client = WebClient.create("http://localhost:8080");

    private Mono<ClientResponse> clientResponse = client.get()
            .uri("/hello")
            .accept(MediaType.TEXT_PLAIN)
            .exchange();

    public String getClientResponse(){
        return ">> result = " + clientResponse.flatMap(res -> res.bodyToMono(String.class)).block();
    }
}
