package br.com.fiap.produtos.repository.projection;

public interface ProdutoProjection {

    Long getId();
    String getNome();
    Double getPreco();
    int getQuantidadeEmEstoque();
}
