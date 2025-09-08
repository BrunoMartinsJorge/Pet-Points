package br.com.petpoints.back.core.security;
import br.com.petpoints.back.core.token.TokenService;
import br.com.petpoints.back.features.usuario.model.UsuarioModel;
import br.com.petpoints.back.features.usuario.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UsuarioRepository usuarioReporitory;

    public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioReporitory) {
        this.tokenService = tokenService;
        this.usuarioReporitory = usuarioReporitory;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        List<String> publicEndpoints = List.of(
                "/conta/login",
                "/conta/register"
        );
        if (publicEndpoints.contains(path)) { // exato
            filterChain.doFilter(request, response);
            return;
        }
        var token = this.recoverToken(request);
        System.out.println("Token: " + token);
        if (token != null) {
            var email = tokenService.validarToken(token);
            System.out.println("Email do token: " + email);
            UsuarioModel user = usuarioReporitory.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
            System.out.println("Autenticando usuário: " + user.getEmail() + " com roles: " + user.getAuthorities());
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;
        return authHeader.substring(7);
    }

}
