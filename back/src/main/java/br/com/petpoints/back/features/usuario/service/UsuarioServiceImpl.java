package br.com.petpoints.back.features.usuario.service;

import br.com.petpoints.back.core.token.TOKENS_ENUM;
import br.com.petpoints.back.core.token.TokenService;
import br.com.petpoints.back.features.clientes.model.ClienteModel;
import br.com.petpoints.back.features.clientes.repository.ClienteRepository;
import br.com.petpoints.back.features.usuario.dto.LoginDto;
import br.com.petpoints.back.features.usuario.exception.UsuarioJaCadastrado;
import br.com.petpoints.back.features.usuario.exception.UsuarioNaoEncontrado;
import br.com.petpoints.back.features.usuario.forms.CadastroForm;
import br.com.petpoints.back.features.usuario.forms.LoginForm;
import br.com.petpoints.back.features.usuario.model.UsuarioModel;
import br.com.petpoints.back.features.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final ClienteRepository clienteRepository;
    private static final String ID = "/{id}";

    UsuarioServiceImpl(UsuarioRepository usuarioRepository, AuthenticationManager authenticationManager, TokenService tokenService, PasswordEncoder passwordEncoder, ClienteRepository clienteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ResponseEntity<?> logarUsuario(LoginForm dados) {
        System.out.println("AQUI 1");
        if (!usuarioRepository.existsByEmail(dados.getEmail()))
            throw new UsuarioNaoEncontrado("Usuário não encontrado!");
        System.out.println("AQUI 2");
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dados.getEmail(), dados.getSenha())
        );
        System.out.println("AQUI 3");
        String token = tokenService.gerarToken((UsuarioModel) auth.getPrincipal());
        System.out.println("AQUI 4");
        LoginDto dadosLogin = new LoginDto(
                token
        );
        System.out.println("AQUI 5");
        return ResponseEntity.ok().body(dadosLogin);
    }

    @Override
    @Transactional
    public ResponseEntity<?> cadastrarUsuario(CadastroForm dados) {
        if (usuarioRepository.existsByEmail(dados.getEmail())) throw new UsuarioJaCadastrado("Usuário já cadastrado!");
        String encryptedPassword = passwordEncoder.encode(dados.getPassword());
        Set<TOKENS_ENUM> roles = new HashSet<>();
        roles.add(TOKENS_ENUM.ROLE_REST_GERENTE);
        UsuarioModel usuarioModel = new UsuarioModel(dados.getEmail(), encryptedPassword, roles);
        usuarioModel.setNome(dados.getNome());
        usuarioModel.setDataNascimento(dados.getDataNascimento());
        usuarioModel.setGenero(dados.getGenero());
        usuarioModel.setCpf(dados.getCpf());
        usuarioModel.setTelefone(dados.getContato());
        usuarioModel.setLogradouro(dados.getLogradouro());
        usuarioModel.setNumero(dados.getNumero());
        usuarioModel.setBairro(dados.getBairro());
        usuarioModel.setComplemento(dados.getComplemento());
        usuarioModel.setCep(dados.getCep());

        usuarioRepository.save(usuarioModel);
        if (roles.contains(TOKENS_ENUM.ROLE_REST_CLIENTE)) {
            clienteRepository.save(new ClienteModel(
                    usuarioModel
            ));
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(usuarioModel.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<?> deletarUsuario(Long idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) throw new UsuarioNaoEncontrado("Usuário não encontrado!");
        usuarioRepository.deleteById(idUsuario);
        return ResponseEntity.noContent().build();
    }
}
