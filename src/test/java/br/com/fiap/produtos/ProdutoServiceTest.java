package br.com.fiap.produtos;


import br.com.fiap.produtos.entity.Produto;
import br.com.fiap.produtos.exception.ProductNotFoundException;
import br.com.fiap.produtos.repository.ProdutoRepository;
import br.com.fiap.produtos.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Test
    public void testSaveProduto() {
        Produto produto = new Produto(1L, "Produto Teste", 1200.0,"Descrição Teste",10);
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);
        Produto savedProduto = produtoService.salvar(produto);
        assertNotNull(savedProduto);
        assertEquals("Produto Teste", savedProduto.getNome());
    }

    @Test
    public void testFindProdutoById() {
        Produto produto = new Produto(1L, "Produto Teste", 1200.0,"Descrição Teste",10);
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produto));
        Produto foundProduto = produtoService.buscarPorId(1L);
        assertNotNull(foundProduto);
        assertEquals("Produto Teste", foundProduto.getNome());
    }

    @Test
    public void testProdutoNotFoundException() {
        when(produtoRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> {
            produtoService.buscarPorId(1L);
        });
    }
}

