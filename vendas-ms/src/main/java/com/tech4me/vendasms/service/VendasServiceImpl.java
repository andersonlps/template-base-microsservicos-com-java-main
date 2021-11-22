package com.tech4me.vendasms.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.tech4me.vendasms.model.Vendas;
import com.tech4me.vendasms.repository.VendasRepository;
import com.tech4me.vendasms.shared.VendasDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendasServiceImpl implements VendasService{

    @Autowired
    VendasRepository repositorioVenda;

    @Override
    public List<VendasDTO> relatorioVendas() {
        List<Vendas> vendas = repositorioVenda.findAll();
        ModelMapper mapper = new ModelMapper();
        return vendas.stream()
        .map(venda -> mapper.map(venda, VendasDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    public Optional<VendasDTO> obterVendaPorId(String idVenda) {
        Optional<Vendas> optionalVenda = repositorioVenda.findById(idVenda);
        if(optionalVenda.isEmpty()){
            throw new InputMismatchException("Não foi possível encontrar nenhuma venda com este id:" + idVenda);
        }
        VendasDTO vendaDto = new ModelMapper().map(optionalVenda.get(), VendasDTO.class);
        return Optional.of(vendaDto);
        }
       
    @Override
    public VendasDTO realizarVenda(VendasDTO vendaDto) {
        ModelMapper mapper = new ModelMapper();
        Vendas venda = mapper.map(vendaDto, Vendas.class);
        venda.setId(null);
        venda = repositorioVenda.save(venda);
        return mapper.map(venda, VendasDTO.class);
        
    }

    @Override
    public VendasDTO atualizar(String idVenda, VendasDTO vendaDto) {
        vendaDto.setId(idVenda);
        ModelMapper mapper = new ModelMapper();
        Vendas venda = mapper.map(vendaDto, Vendas.class);
        venda = repositorioVenda.save(venda);
        return mapper.map(venda, VendasDTO.class);
    }

    @Override
    public void delete(String idVenda) {
        repositorioVenda.deleteById(idVenda);
    }
    
}
