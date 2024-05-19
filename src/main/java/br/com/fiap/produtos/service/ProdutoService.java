package br.com.fiap.produtos.service;

import br.com.fiap.produtos.entity.Produto;
import br.com.fiap.produtos.exception.ProductNotFoundException;
import br.com.fiap.produtos.repository.ProdutoRepository;
import br.com.fiap.produtos.repository.projection.ProdutoProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;


@RequiredArgsConstructor
@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Transactional(readOnly = true)
    public Page<ProdutoProjection> buscarTodos(Pageable pageable) {
        return produtoRepository.findAllPageable(pageable);
    }

    @Transactional(readOnly = true)
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(String.format("Produto id=%s não encontrado no sistema", id))
        );
    }

    @Transactional(readOnly = true)
    public Page<ProdutoProjection> buscarProdutosLikeNome(String nome, Pageable pageable) {
        return produtoRepository.findByNomeContaining(nome, pageable);
    }

    @Transactional
    public void deletarPorId(Long id) {
       Produto produto = buscarPorId(id) ;
       if (produto != null) {
           produtoRepository.deleteById(id);
       }
    }

}
