package com.linkhub.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProdutoRequest {
    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @Positive
    private BigDecimal preco;

    @NotBlank
    private String imagem;

    @NotBlank
    private String linkAfiliado;

    @NotNull(message = "Você deve informar se a oferta está ativa ou não.")
    private Boolean ofertaAtiva;

}
