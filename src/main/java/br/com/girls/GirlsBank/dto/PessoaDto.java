package br.com.girls.GirlsBank.dto;

import lombok.Getter;

@Getter
public class PessoaDto {
    private Integer id;
    private String nomeCompleto;
    private String email;
    private String senha;
    private Long cpf;
    private Long telefone;
}