package com.tech4me.vendasms.view.model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class VendasModeloResponse {
    
    private String id;

    private int codigoProduto;

    private int quantidade;

    private Double valorProduto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(Double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public Double getValorTotal() {
        return valorProduto*quantidade;
    }

    public void getDataVenda() {
        SimpleDateFormat hms = new SimpleDateFormat("dd/MM/yyyy"); 
        System.out.println("Vendido em: " + hms.format(LocalDateTime.now()));
    }   
}
