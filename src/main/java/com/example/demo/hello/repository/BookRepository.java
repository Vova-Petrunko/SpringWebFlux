package com.example.demo.hello.repository;

import com.example.demo.hello.models.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface BookRepository extends ReactiveCrudRepository<Book, String> {
    @Query("{ id: { $exists: true }}")
    Flux<Book> retrieveAllQuotesPaged(final Pageable page);
}
