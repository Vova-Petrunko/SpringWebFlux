package com.example.demo.hello.routers;

import com.example.demo.hello.handlers.BookHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class BookRouter {
    @Bean
    public RouterFunction<ServerResponse> route(BookHandler bookHandler) {
        return RouterFunctions.route(GET("/quote/{id}").and(accept(APPLICATION_JSON)), bookHandler::getById)
                .andRoute(GET("/quote").and(accept(APPLICATION_JSON)), bookHandler::findAll)
                .andRoute(POST("/quote").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), bookHandler::post)
                .andRoute(PUT("/quote/{id}").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), bookHandler::put)
                .andRoute(DELETE("/quote/{id}"), bookHandler::deleteQuote);
    }
}
