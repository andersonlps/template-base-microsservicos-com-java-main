package com.tech4me.produtoms.repository;

import com.tech4me.produtoms.model.Produto;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<Produto, String>{
}
