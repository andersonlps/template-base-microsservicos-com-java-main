package com.tech4me.produtoms.service;

import java.util.List;
import java.util.Optional;

import com.tech4me.produtoms.shared.ProdutoDTO;

import org.springframework.stereotype.Service;

@Service
public interface ProdutoService {
    
    List<ProdutoDTO> obterTodos();

    Optional<ProdutoDTO> obterPorId(String idProduto);

    ProdutoDTO cadastrarProduto(ProdutoDTO produtoDto);

    ProdutoDTO atualizarProduto(String idProduto, ProdutoDTO produtoDto);

    void delete(String idProduto);
}
