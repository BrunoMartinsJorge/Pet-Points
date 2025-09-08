package br.com.petpoints.back.shared.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pessoa {
    protected LocalDate dataNascimento;
}
