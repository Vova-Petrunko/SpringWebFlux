package com.example.demo.hello.handlers;

import com.example.demo.hello.models.Book;
import com.example.demo.hello.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
public class BookHandler {
    private final BookService bookService;


    @Autowired
    public BookHandler(BookService bookService) {
        this.bookService = bookService;
    }

    public Mono<ServerResponse> getById(ServerRequest request){

        final String id = request.pathVariable("id");
        final Mono<Book> quote = bookService.findById(id);

        return quote.flatMap(q -> ok().contentType(APPLICATION_JSON).body(fromPublisher(quote, Book.class)))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> put(ServerRequest request){

        final String id = request.pathVariable("id");
        final Mono<Book> quote = bookService.findById(id);

        return bookService.findById(id)
                .flatMap(
                        old ->
                                ok().contentType(APPLICATION_JSON)
                                        .body(
                                                fromPublisher(
                                                        quote.map(q -> new Book(q, id))
                                                                .flatMap(q -> bookService.update(old, q)),
                                                        Book.class)))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> post(ServerRequest request){
        final Mono<Book> quote = request.bodyToMono(Book.class);
        final String id = UUID.randomUUID().toString();

        return created(UriComponentsBuilder.fromPath("/quote" + id).build().toUri())
                .contentType(APPLICATION_JSON)
                .body(
                        fromPublisher(
                                quote.map(q -> new Book(q, id)).flatMap(bookService::save), Book.class));
    }

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ok().contentType(APPLICATION_JSON).body(fromPublisher(bookService.findAll(), Book.class));
    }

    public Mono<ServerResponse> deleteQuote(ServerRequest request){
        final String id = request.pathVariable("id");
        return bookService.findById(id)
                .flatMap(quote -> noContent().build(bookService.delete(quote.getId())))
                .switchIfEmpty(notFound().build());
    }
}
