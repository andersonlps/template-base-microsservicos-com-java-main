package com.tech4me.produtoms.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import com.tech4me.produtoms.model.Produto;
import com.tech4me.produtoms.service.ProdutoService;
import com.tech4me.produtoms.shared.ProdutoDTO;
import com.tech4me.produtoms.view.model.ProdutoModeloRequest;
import com.tech4me.produtoms.view.model.ProdutoModeloResponse;
import org.springframework.http.HttpStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService servicoProduto;

    @PostMapping
    public ResponseEntity<ProdutoModeloResponse> cadastrarProduto(@RequestBody @Valid ProdutoModeloRequest produto) {
        ModelMapper mapper = new ModelMapper();
        ProdutoDTO produtoDto = mapper.map(produto, ProdutoDTO.class);
        produtoDto = servicoProduto.cadastrarProduto(produtoDto);
        return new ResponseEntity<>(mapper.map(produtoDto, ProdutoModeloResponse.class), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<ProdutoModeloResponse>> obterTodos() {
        List<ProdutoDTO> produtos = servicoProduto.obterTodos();

        if(produtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<ProdutoModeloResponse> response = produtos.stream()
                    .map(produto -> mapper.map(produto, ProdutoModeloResponse.class))
                    .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModeloRequest> obterPorId(@PathVariable String id) {
        Optional<ProdutoDTO> produto = servicoProduto.obterPorId(id);

        if(produto.isPresent()) {
            return new ResponseEntity<>(
                new ModelMapper().map(produto.get(), ProdutoModeloRequest.class), 
                HttpStatus.OK
            );
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoModeloRequest> atualizarProduto(@PathVariable String id, @Valid
         @RequestBody Produto produto) {
        ModelMapper mapper = new ModelMapper();
        ProdutoDTO produtoDto = mapper.map(produto, ProdutoDTO.class);
        produtoDto = servicoProduto.atualizarProduto(id, produtoDto);
        return new ResponseEntity<>(mapper.map(produtoDto, ProdutoModeloRequest.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        servicoProduto.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
    
    
}
