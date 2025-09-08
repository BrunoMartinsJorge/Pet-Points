package br.com.petpoints.back.features.usuario.model;

import br.com.petpoints.back.core.token.TOKENS_ENUM;
import br.com.petpoints.back.shared.enums.GeneroEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@NoArgsConstructor
public class UsuarioModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "O campo 'email' não pode ser nulo!")
    private String email;

    @NotNull(message = "O campo 'senha' não pode ser nulo!")
    private String senha;

    @NotNull(message = "O campo 'nome' não pode ser nulo!")
    private String nome;

    @Column(unique = true)
    @NotNull(message = "O campo 'cpf' não pode ser nulo!")
    private String cpf;

    @NotNull(message = "O campo 'telefone' não pode ser nulo!")
    private String telefone;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O campo 'genero' não pode ser nulo!")
    private GeneroEnum genero;

    @NotNull(message = "O campo 'data_nascimento' não pode ser nulo!")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @NotNull(message = "O campo 'numero' não pode ser nulo!")
    private Integer numero;

    private String cep;

    @NotBlank(message = "O campo 'logradouro' não pode ser vazio!")
    private String logradouro;

    @NotBlank(message = "O campo 'bairro' não pode ser vazio!")
    private String bairro;

    private String complemento;

    @CreationTimestamp
    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @ElementCollection(targetClass = TOKENS_ENUM.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<TOKENS_ENUM> roles = new HashSet<>();

    public UsuarioModel(String email, String senha, Set<TOKENS_ENUM> roles) {
        this.email = email;
        this.senha = senha;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(roleEnum -> {
                    String roleString = roleEnum.getRole().toUpperCase();
                    return new SimpleGrantedAuthority("ROLE_" + roleString);
                })
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
