package com.tech4me.vendasms.service;

import java.util.List;
import java.util.Optional;
import com.tech4me.vendasms.shared.VendasDTO;
import org.springframework.stereotype.Service;

@Service
public interface VendasService {

    List<VendasDTO> relatorioVendas();

    Optional<VendasDTO> obterVendaPorId(String idVenda);

    VendasDTO realizarVenda(VendasDTO vendaDto);

    VendasDTO atualizar(String idVenda, VendasDTO vendaDto);

    void delete(String idVenda);
    
}
