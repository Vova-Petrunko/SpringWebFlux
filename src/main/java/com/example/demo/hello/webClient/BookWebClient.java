package com.example.demo.hello.webClient;

import com.example.demo.hello.models.Book;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;

public class BookWebClient {
    private WebClient client = WebClient.create("http://localhost:8080");
    public void doStuff() {
        // POST
        final Book record = new Book(UUID.randomUUID().toString(), "Happy Three Friends", "John Doe");
        final Mono<ClientResponse> postResponse =
                client
                        .post()
                        .uri("/quote")
                        .body(Mono.just(record), Book.class)
                        .accept(APPLICATION_JSON)
                        .exchange();
        postResponse
                .map(ClientResponse::statusCode)
                .subscribe(status -> System.out.println("POST: " + status.getReasonPhrase()));
        // GET
        client
                .get()
                .uri("/quote/{id}", "a4f66fe5-7c1b-4bcf-89b4-93d8fcbc52a4")
                .accept(APPLICATION_JSON)
                .exchange()
                .flatMap(response -> response.bodyToMono(Book.class))
                .subscribe(person -> System.out.println("GET: " + person));
        // ALL
        client
                .get()
                .uri("/quote")
                .accept(APPLICATION_JSON)
                .exchange()
                .flatMapMany(response -> response.bodyToFlux(Book.class))
                .subscribe(person -> System.out.println("ALL: " + person));
        // PUT
        final Book updated = new Book(UUID.randomUUID().toString(), "How I met your mom", "Peter Parker");
        client
                .put()
                .uri("/quote/{id}", "ec2212fc-669e-42ff-9c51-69782679c9fc")
                .body(Mono.just(updated), Book.class)
                .accept(APPLICATION_JSON)
                .exchange()
                .map(ClientResponse::statusCode)
                .subscribe(response -> System.out.println("PUT: " + response.getReasonPhrase()));
        // DELETE
        client
                .delete()
                .uri("/quote/{id}", "ec2212fc-669e-42ff-9c51-69782679c9fc")
                .exchange()
                .map(ClientResponse::statusCode)
                .subscribe(status -> System.out.println("DELETE: " + status));
    }
}
