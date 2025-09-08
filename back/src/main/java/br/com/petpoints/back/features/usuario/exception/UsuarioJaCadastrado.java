package br.com.petpoints.back.features.usuario.exception;

public class UsuarioJaCadastrado extends RuntimeException {
    public UsuarioJaCadastrado(String mensagem) {
        super(mensagem);
    }
}
