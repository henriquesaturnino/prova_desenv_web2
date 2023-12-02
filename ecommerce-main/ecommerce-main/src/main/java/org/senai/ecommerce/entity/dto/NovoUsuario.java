package org.senai.ecommerce.entity.dto;

import lombok.Data;

@Data
public class NovoUsuario {
    private String nome;
    private String senha;
    private String email;
    private String permissao;
}
