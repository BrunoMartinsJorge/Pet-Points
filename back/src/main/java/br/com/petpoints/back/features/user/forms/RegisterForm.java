package br.com.petpoints.back.features.user.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RegisterForm {

    @NotNull(message = "O email não pode ser nulo!")
    @NotBlank(message = "O email não pode ser vazio!")
    private String email;

    @NotNull(message = "A senha não pode ser nula!")
    @NotBlank(message = "A senha não pode ser vazia!")
    private String password;

    @NotNull(message = "A senha de confirmação não pode ser nula!")
    @NotBlank(message = "A senha de confirmação não pode ser vazia!")
    private String confirmPassword;

    @NotNull(message = "O nome não pode ser nulo!")
    @NotBlank(message = "O nome não pode ser vazio!")
    private String nome;

    @NotNull(message = "A data de nascimento não pode ser nula!")
    @NotBlank(message = "A data de nascimento não pode ser vazio!")
    private LocalDate dataNascimento;

    @NotNull(message = "O telefone não pode ser nulo!")
    @NotBlank(message = "O telefone não pode ser vazio!")
    private String telefone;

    @NotNull(message = "O CPF não pode ser nulo!")
    @NotBlank(message = "O CPF não pode ser vazio!")
    private String cpf;

    @NotNull(message = "O endereço não pode ser nulo!")
    @NotBlank(message = "O endereço não pode ser vazio!")
    private String endereco;
}
