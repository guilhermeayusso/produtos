package br.com.fiap.produtos;

import br.com.fiap.produtos.controller.ProdutoController;
import br.com.fiap.produtos.entity.Produto;
import br.com.fiap.produtos.exception.ProductNotFoundException;
import br.com.fiap.produtos.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @Test
    public void testGetProdutoById() throws Exception {
        Produto produto = new Produto(1L, "Produto Teste", 1200.0,"Descrição Teste",10);
        when(produtoService.buscarPorId(anyLong())).thenReturn(produto);

        mockMvc.perform(get("/api/v1/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Produto Teste"));
    }

    @Test
    public void testGetProdutoByIdNotFound() throws Exception {
        when(produtoService.buscarPorId(anyLong())).thenThrow(new ProductNotFoundException("Produto não encontrado"));

        mockMvc.perform(get("/api/v1/produtos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateProduto() throws Exception {
        Produto produto = new Produto(1L, "Produto Teste", 1200.0,"Descrição Teste",10);
        when(produtoService.salvar(any(Produto.class))).thenReturn(produto);

        mockMvc.perform(post("/api/v1/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Produto Teste\", \"preco\": 100.0, \"descricao\": \"Descrição Teste\", \"quantidadeEmEstoque\": 10}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Produto Teste"));
    }

    @Test
    public void testCreateProdutoInvalid() throws Exception {
        mockMvc.perform(post("/api/v1/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"\", \"preco\": -1.0}"))
                .andExpect(status().is(422));
    }
}

