package com.tech4me.produtoms.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.tech4me.produtoms.model.Produto;
import com.tech4me.produtoms.repository.ProdutoRepository;
import com.tech4me.produtoms.shared.ProdutoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    ProdutoRepository repositorioProduto;

    @Override
    public List<ProdutoDTO> obterTodos() {
        List<Produto> produtos = repositorioProduto.findAll();
        ModelMapper mapper = new ModelMapper();
        return produtos.stream()
        .map(produto -> mapper.map(produto, ProdutoDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    public Optional<ProdutoDTO> obterPorId(String idProduto) {
        Optional<Produto> optionalProduto = repositorioProduto.findById(idProduto);
        if(optionalProduto.isEmpty()){
            throw new InputMismatchException("Não foi possível encontrar nenhum produto com este id:" + idProduto);
        }
        ProdutoDTO produtoDto = new ModelMapper().map(optionalProduto.get(), ProdutoDTO.class);
        return Optional.of(produtoDto);
        }

    @Override
    public ProdutoDTO cadastrarProduto(ProdutoDTO produtoDto) {
        ModelMapper mapper = new ModelMapper();
        Produto produto = mapper.map(produtoDto, Produto.class);
        produto.setId(null);
        produto = repositorioProduto.save(produto);
        return mapper.map(produto, ProdutoDTO.class);
    }

    @Override
    public ProdutoDTO atualizarProduto(String idProduto, ProdutoDTO produtoDto) {
        produtoDto.setId(idProduto);
        ModelMapper mapper = new ModelMapper();
        Produto produto = mapper.map(produtoDto, Produto.class);
        produto = repositorioProduto.save(produto);
        return mapper.map(produto, ProdutoDTO.class);
    }

    @Override
    public void delete(String idProduto) {
        repositorioProduto.deleteById(idProduto);
        
    }
    
}
