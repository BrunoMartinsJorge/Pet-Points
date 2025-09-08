package br.com.petpoints.back.shared.exception;

public class TokenExpirada extends RuntimeException{
    public TokenExpirada(String message) {
        super(message);
    }
}
