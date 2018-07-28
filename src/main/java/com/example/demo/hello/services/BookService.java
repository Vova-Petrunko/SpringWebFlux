package com.example.demo.hello.services;

import com.example.demo.hello.models.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
    Mono<Book> findById(String id);

    Flux<Book> findAll();

    Mono<Book> save(Book book);

    Mono<Void> delete(String id);

    Mono<Book> update(Book oldBook, Book newBook);
}
