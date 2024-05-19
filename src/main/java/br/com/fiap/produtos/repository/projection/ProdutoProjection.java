package br.com.fiap.produtos.repository.projection;

public interface ProdutoProjection {

    String getNome();
    Double getPreco();
    int getQuantidadeEmEstoque();
}
