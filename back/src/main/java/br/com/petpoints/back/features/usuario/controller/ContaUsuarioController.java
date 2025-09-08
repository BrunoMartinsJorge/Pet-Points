package br.com.petpoints.back.features.usuario.controller;

import br.com.petpoints.back.features.usuario.forms.CadastroForm;
import br.com.petpoints.back.features.usuario.forms.LoginForm;
import br.com.petpoints.back.features.usuario.service.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
public class ContaUsuarioController {

    private final UsuarioServiceImpl usuarioServiceImpl;

    public ContaUsuarioController(UsuarioServiceImpl usuarioServiceImpl) {
        this.usuarioServiceImpl = usuarioServiceImpl;
    }

    @PostMapping("/login")
    public ResponseEntity<?> logar(@RequestBody @Valid LoginForm dados){
        return ResponseEntity.ok().body(usuarioServiceImpl.logarUsuario(dados));
    }

    @PostMapping("/register")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastroForm dados){
        System.out.println("BATEU AQUI!");
        return ResponseEntity.ok().body(usuarioServiceImpl.cadastrarUsuario(dados));
    }

    @PreAuthorize("hasRole('USUARIO')")
    @DeleteMapping("/excluir-conta/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        return ResponseEntity.ok().body(usuarioServiceImpl.deletarUsuario(id));
    }
}
