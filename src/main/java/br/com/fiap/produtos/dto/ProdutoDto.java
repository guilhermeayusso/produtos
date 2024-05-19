package br.com.fiap.produtos.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link br.com.fiap.produtos.entity.Produto}
 */
@Value
@Getter
@Setter
@AllArgsConstructor
public class ProdutoDto implements Serializable {

    private Long id;

    @NotBlank(message = "O nome do produto não pode estar vazio.")
    @Size(min = 2, max = 100, message = "O nome do produto deve ter entre 2 e 100 caracteres.")
    private String nome;

    @NotNull(message = "O preço do produto não pode ser nulo.")
    @PositiveOrZero(message = "O preço do produto deve ser um valor não negativo.")
    private double preco;

    @NotBlank(message = "A descricao do produto não pode estar vazio.")
    @Size(max = 500, message = "A descrição do produto não deve ultrapassar 500 caracteres.")
    private String descricao;

    @NotNull(message = "A quantidade em estoque não pode ser nula.")
    @Min(value = 0, message = "A quantidade em estoque não pode ser negativa.")
    private int quantidadeEmEstoque;
}