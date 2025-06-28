package com.franquicias.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.franquicias.model.Franquicia;

import reactor.core.publisher.Mono;

public interface FranquiciaRepository extends ReactiveMongoRepository<Franquicia, String> {
    Mono<Franquicia> findByNombre(String nombre);
}