package com.linkhub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ProdutoResponse {
    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String imagem;
    private String linkAfiliado;
    private Boolean ofertaAtiva;
    private LocalDateTime dataCadastro;
}