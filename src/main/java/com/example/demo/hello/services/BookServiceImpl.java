package com.example.demo.hello.services;

import com.example.demo.hello.models.Book;
import com.example.demo.hello.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(@Autowired BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Mono<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public Flux<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Mono<Book> save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Mono<Void> delete(String id) {
        return bookRepository.deleteById(id);
    }

    @Override
    public Mono<Book> update(Book oldBook, Book newBook) {
        return bookRepository.save(newBook);
    }
}
