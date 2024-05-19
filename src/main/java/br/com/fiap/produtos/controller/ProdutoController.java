package br.com.fiap.produtos.controller;

import br.com.fiap.produtos.dto.PageableDto;
import br.com.fiap.produtos.dto.ProdutoDto;
import br.com.fiap.produtos.dto.mapper.PageableMapper;
import br.com.fiap.produtos.dto.mapper.ProdutoMapper;
import br.com.fiap.produtos.entity.Produto;
import br.com.fiap.produtos.repository.projection.ProdutoProjection;
import br.com.fiap.produtos.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDto> cadastrarProduto(@Valid @RequestBody ProdutoDto produtoDto) {
        Produto produto = ProdutoMapper.INSTANCE.toEntity(produtoDto);
        return ResponseEntity.ok(ProdutoMapper.INSTANCE.toDto(produtoService.salvar(produto)));
    }

    @GetMapping
    public ResponseEntity<PageableDto> buscarTodosProdutos(
            @PageableDefault(size = 5, sort = {"nome"}) Pageable pageable) {
        Page<ProdutoProjection> clientes = produtoService.buscarTodos(pageable);
        return ResponseEntity.ok(PageableMapper.toDto(clientes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> buscarPorId(@PathVariable Long id) {
        Produto produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(ProdutoMapper.INSTANCE.toDto(produto));
    }

    @GetMapping("/buscar-por-nome/{nome}")
    public ResponseEntity<PageableDto> buscarTodosProdutos(@PathVariable String nome,
            @PageableDefault(size = 5, sort = {"nome"}) Pageable pageable) {
        Page<ProdutoProjection> clientes = produtoService.buscarProdutosLikeNome(nome,pageable);
        return ResponseEntity.ok(PageableMapper.toDto(clientes));
    }


}
