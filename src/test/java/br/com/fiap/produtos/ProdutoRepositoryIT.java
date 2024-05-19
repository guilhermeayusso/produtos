package br.com.fiap.produtos;


import br.com.fiap.produtos.entity.Produto;
import br.com.fiap.produtos.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProdutoRepositoryIT {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    public void testSaveAndFindProduto() {
        Produto produto = new Produto(1L, "Produto Teste", 1200.0,"Descrição Teste",10);
        produtoRepository.save(produto);

        Optional<Produto> foundProduto = produtoRepository.findById(produto.getId());
        assertTrue(foundProduto.isPresent());
        assertEquals("Produto Teste", foundProduto.get().getNome());
        assertEquals("Descrição Teste", foundProduto.get().getDescricao());
        assertEquals(10, foundProduto.get().getQuantidadeEmEstoque());
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

