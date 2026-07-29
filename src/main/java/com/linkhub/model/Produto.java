package com.linkhub.model;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull(message = "O preço é obrigatório.")
    @Positive(message = "O preço deve ser maior que zero.")
    private BigDecimal preco;

    @NotBlank
    private String imagem;

    private LocalDateTime dataCadastro;

    @NotBlank
    private String linkAfiliado;

    @NotNull(message = "Você deve informar se a oferta está ativa ou não.")
    private Boolean ofertaAtiva;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();}

}

