package com.tech4me.vendasms.repository;

import com.tech4me.vendasms.model.Vendas;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VendasRepository extends MongoRepository<Vendas, String> {
    
}
