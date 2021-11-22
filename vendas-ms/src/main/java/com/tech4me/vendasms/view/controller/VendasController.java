package com.tech4me.vendasms.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import com.tech4me.vendasms.model.Vendas;
import com.tech4me.vendasms.service.VendasService;
import com.tech4me.vendasms.shared.VendasDTO;
import com.tech4me.vendasms.view.model.VendasModeloRequest;
import com.tech4me.vendasms.view.model.VendasModeloResponse;
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
@RequestMapping("/api/vendas")
public class VendasController {

    @Autowired
    private VendasService servicoVenda;

    @PostMapping
    public ResponseEntity<VendasModeloResponse> realizarVenda(@RequestBody @Valid VendasModeloRequest venda) {
        ModelMapper mapper = new ModelMapper();
        VendasDTO vendaDto = mapper.map(venda, VendasDTO.class);
        vendaDto = servicoVenda.realizarVenda(vendaDto);
        return new ResponseEntity<>(mapper.map(vendaDto, VendasModeloResponse.class), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<VendasModeloResponse>> obterTodos() {
        List<VendasDTO> vendas = servicoVenda.relatorioVendas();

        if(vendas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<VendasModeloResponse> response = vendas.stream()
                    .map(venda -> mapper.map(venda, VendasModeloResponse.class))
                    .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VendasModeloRequest> obterPorId(@PathVariable String id) {
        Optional<VendasDTO> venda = servicoVenda.obterVendaPorId(id);

        if(venda.isPresent()) {
            return new ResponseEntity<>(
                new ModelMapper().map(venda.get(), VendasModeloRequest.class), 
                HttpStatus.OK
            );
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendasModeloRequest> atualizar(@PathVariable String id, @Valid
         @RequestBody Vendas venda) {
        ModelMapper mapper = new ModelMapper();
        VendasDTO vendaDto = mapper.map(venda, VendasDTO.class);
        vendaDto = servicoVenda.atualizar(id, vendaDto);

        return new ResponseEntity<>(mapper.map(vendaDto, VendasModeloRequest.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        servicoVenda.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 
    
}
