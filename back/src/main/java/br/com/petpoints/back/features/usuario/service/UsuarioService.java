package br.com.petpoints.back.features.usuario.service;

import br.com.petpoints.back.features.usuario.forms.CadastroForm;
import br.com.petpoints.back.features.usuario.forms.LoginForm;
import org.springframework.http.ResponseEntity;

public interface UsuarioService {

    ResponseEntity<?> logarUsuario(LoginForm form);
    ResponseEntity<?> cadastrarUsuario(CadastroForm form);
    ResponseEntity<?> deletarUsuario(Long idUsuario);
}
