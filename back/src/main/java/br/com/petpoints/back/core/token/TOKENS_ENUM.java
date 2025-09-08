package br.com.petpoints.back.core.token;

import lombok.Getter;

@Getter
public enum TOKENS_ENUM {

    ROLE_REST_FUNCIONARIO("funcionario"),
    ROLE_REST_GERENTE("gerente"),
    ROLE_REST_ATENDENTE("atendente"),
    ROLE_REST_DOUTORES("doutores"),
    ROLE_REST_CLIENTE("cliente"),
    ROLE_REST_USUARIO("usuario");

    private final String role;

    TOKENS_ENUM(String role) {
        this.role = role;
    }
}
