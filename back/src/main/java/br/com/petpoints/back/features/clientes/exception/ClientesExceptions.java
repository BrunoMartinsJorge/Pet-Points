package br.com.petpoints.back.features.clientes.exception;

import br.com.petpoints.back.shared.exception.StandardException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ClientesExceptions {

    @ExceptionHandler(ClienteNaoEncontrado.class)
    public ResponseEntity<StandardException> ClienteNaoEncontrado(ClienteNaoEncontrado ex, HttpServletRequest request) {
        StandardException error = new StandardException(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ClienteJaCadastrado.class)
    public ResponseEntity<StandardException> ClienteJaCadastrado(ClienteJaCadastrado ex, HttpServletRequest request) {
        StandardException error = new StandardException(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
