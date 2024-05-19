package br.com.fiap.produtos.repository;

import br.com.fiap.produtos.entity.Produto;
import br.com.fiap.produtos.repository.projection.ProdutoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("select p from Produto p")
    Page<ProdutoProjection> findAllPageable(Pageable pageable);

    Page<ProdutoProjection> findByNomeContaining(String nome, Pageable pageable);
}
