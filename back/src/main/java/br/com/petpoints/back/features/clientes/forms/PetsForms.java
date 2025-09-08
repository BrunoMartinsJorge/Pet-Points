package br.com.petpoints.back.features.clientes.forms;

import br.com.petpoints.back.shared.enums.GeneroEnum;
import br.com.petpoints.back.shared.enums.PetEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetsForms {
    private String nome;
    private GeneroEnum genero;
    private PetEnum tipoAnimal;
    private LocalDate dataNascimento;
    private int idade;
    private String raca;
    private String problemasSaude;
}
