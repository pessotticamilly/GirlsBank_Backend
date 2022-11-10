package br.com.girls.GirlsBank.dto;

import br.com.girls.GirlsBank.model.entities.Pessoa;
import lombok.Getter;

@Getter
public class ContaDto {
    private Integer numero;
    private Double saldo;
    private Pessoa pessoa;
}