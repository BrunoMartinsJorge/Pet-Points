package br.com.petpoints.back.features.clientes.exception;

public class ClienteJaCadastrado extends RuntimeException {
    public ClienteJaCadastrado(String mensagem) {
        super(mensagem);
    }
}
