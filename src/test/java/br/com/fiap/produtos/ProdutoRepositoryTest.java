package br.com.fiap.produtos;



import br.com.fiap.produtos.entity.Produto;
import br.com.fiap.produtos.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    public void testSaveProduto() {
        Produto produto = new Produto(1L, "Produto Teste", 1200.0,"Descrição Teste",10);
        Produto savedProduto = produtoRepository.save(produto);
        assertNotNull(savedProduto.getId());
        assertEquals("Produto Teste", savedProduto.getNome());
    }

    @Test
    public void testFindById() {
        Produto produto = new Produto(1L, "Produto Teste", 1200.0,"Descrição Teste",10);
        produtoRepository.save(produto);
        Optional<Produto> foundProduto = produtoRepository.findById(produto.getId());
        assertTrue(Optional.of(foundProduto).isPresent());
    }

    @Test
    public void testDeleteProduto() {
        Produto produto = new Produto(1L, "Produto Teste", 1200.0,"Descrição Teste",10);
        produtoRepository.save(produto);
        produtoRepository.deleteById(produto.getId());
        Optional<Produto> foundProduto = produtoRepository.findById(produto.getId());
        assertFalse(foundProduto.isPresent());
    }
}

