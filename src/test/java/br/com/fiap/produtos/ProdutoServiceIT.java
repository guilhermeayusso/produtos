package br.com.fiap.produtos;


import br.com.fiap.produtos.entity.Produto;
import br.com.fiap.produtos.exception.ProductNotFoundException;
import br.com.fiap.produtos.repository.ProdutoRepository;
import br.com.fiap.produtos.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProdutoServiceIT {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    public void testSaveProduto() {
        Produto produto = new Produto(1L, "Produto Teste", 1200.0,"Descrição Teste",10);
        Produto savedProduto = produtoService.salvar(produto);
        assertNotNull(savedProduto);
        assertEquals("Produto Teste", savedProduto.getNome());
        assertEquals("Descrição Teste", savedProduto.getDescricao());
        assertEquals(10, savedProduto.getQuantidadeEmEstoque());
    }

    @Test
    public void testFindProdutoById() {
        Produto produto = new Produto(1L, "Produto Teste", 1200.0,"Descrição Teste",10);
        produtoRepository.save(produto);

        Produto foundProduto = produtoService.buscarPorId(produto.getId());
        assertNotNull(foundProduto);
        assertEquals("Produto Teste", foundProduto.getNome());
        assertEquals("Descrição Teste", foundProduto.getDescricao());
        assertEquals(10, foundProduto.getQuantidadeEmEstoque());
    }

    @Test
    public void testProdutoNotFoundException() {
        assertThrows(ProductNotFoundException.class, () -> {
            produtoService.buscarPorId(999L);
        });
    }
}

