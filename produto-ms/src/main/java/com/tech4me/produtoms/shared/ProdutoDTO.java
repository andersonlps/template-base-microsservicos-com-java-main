package com.tech4me.produtoms.shared;

public class ProdutoDTO {
    
    private String id;

    private String nome;

    private int codigoProduto;

    private Double preco;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigo(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
