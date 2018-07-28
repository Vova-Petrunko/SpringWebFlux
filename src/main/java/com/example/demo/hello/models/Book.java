package com.example.demo.hello.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Book {

    @Id
    private String id;
    private String name;
    private String author;

    public Book() {
    }

    public Book(Book book, String id) {
        this.name = book.name;
        this.author = book.author;
        this.id = id;
    }

    public Book(String id, String book, String author) {
        this.id = id;
        this.name = book;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }
}
