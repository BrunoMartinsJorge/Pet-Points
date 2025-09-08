package br.com.petpoints.back.core.token;

import br.com.petpoints.back.features.usuario.exception.UsuarioNaoEncontrado;
import br.com.petpoints.back.features.usuario.model.UsuarioModel;
import br.com.petpoints.back.features.usuario.repository.UsuarioRepository;
import br.com.petpoints.back.shared.exception.TokenExpirada;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(UsuarioModel usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("vitapet-api")
                    .withSubject(usuario.getEmail())
                    .withClaim("id_usuario", usuario.getId())
                    .withClaim("permissoes", usuario.getRoles().stream()
                            .map(TOKENS_ENUM::name)
                            .collect(Collectors.toList()))
                    .withExpiresAt(geradorDeDataDeExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro na hora da geração da token!", e);
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            System.out.println(token);
            return JWT.require(algorithm)
                    .withIssuer("vitapet-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new TokenExpirada("A token está expirada!");
        }
    }

    private Instant geradorDeDataDeExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    @Service
    public static class AuthorizationService implements UserDetailsService {

        private final UsuarioRepository usuarioReporitory;

        public AuthorizationService(UsuarioRepository usuarioReporitory) {
            this.usuarioReporitory = usuarioReporitory;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return usuarioReporitory.findByEmail(username)
                    .orElseThrow(() -> new UsuarioNaoEncontrado("Usuário não encontrado com email: " + username));
        }
    }

    public List<TOKENS_ENUM> pegarRole(String token) {
        String[] roles = JWT.decode(token).getClaim("permissoes").asArray(String.class);
        if (roles == null) return Collections.emptyList();
        return Arrays.stream(roles)
                .map(r -> {
                    try {
                        return TOKENS_ENUM.valueOf(r);
                    } catch (IllegalArgumentException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

}
