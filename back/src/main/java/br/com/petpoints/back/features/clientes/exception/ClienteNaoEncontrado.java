package br.com.petpoints.back.features.clientes.exception;

public class ClienteNaoEncontrado extends RuntimeException{
    public ClienteNaoEncontrado(String mensagem) {
        super(mensagem);
    }
}
