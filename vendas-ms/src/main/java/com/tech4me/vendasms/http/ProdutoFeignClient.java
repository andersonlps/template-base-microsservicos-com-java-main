package com.tech4me.vendasms.http;

import com.tech4me.vendasms.shared.Produto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "produto-ms")
public interface ProdutoFeignClient {
    

    @PostMapping
    Produto obterCodigo(@PathVariable String codigoProduto);

    
  
}

