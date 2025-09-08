package br.com.petpoints.back.features.usuario.forms;

import br.com.petpoints.back.shared.enums.GeneroEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastroForm {

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "E-mail inválido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    private String password;

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    private String cpf;

    @NotBlank(message = "O contato é obrigatório.")
    private String contato;

    @NotNull(message = "A data de nascimento é obrigatória.")
    private LocalDate dataNascimento;

    @NotNull(message = "O gênero é obrigatório.")
    private GeneroEnum genero;

    private String cep;

    @NotNull(message = "O logradouro é obrigatório.")
    private String logradouro;

    @NotNull(message = "O número é obrigatório.")
    private Integer numero;

    private String complemento;

    @NotNull(message = "O bairro é obrigatório.")
    private String bairro;
}
