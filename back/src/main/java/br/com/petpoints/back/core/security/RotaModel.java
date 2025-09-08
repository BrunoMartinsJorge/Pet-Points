package br.com.petpoints.back.core.security;

import br.com.petpoints.back.core.token.TOKENS_ENUM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RotaModel {
    private String path;
    private List<TOKENS_ENUM> permissoes;
}