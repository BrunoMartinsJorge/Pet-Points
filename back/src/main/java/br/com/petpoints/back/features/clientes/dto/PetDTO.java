package br.com.petpoints.back.features.clientes.dto;

import br.com.petpoints.back.features.clientes.model.PetsModel;
import br.com.petpoints.back.shared.enums.GeneroEnum;
import br.com.petpoints.back.shared.enums.PetEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PetDTO {

    private Long id;
    private String nome;
    private PetEnum tipoAnimal;
    private GeneroEnum genero;
    private int idade;
    private LocalDate dataNascimento;
    private String raca;
    private String problemasSaude;

    public PetDTO(Long id, String nome, PetEnum tipoAnimal, GeneroEnum genero, int idade, LocalDate dataNascimento, String raca, String problemasSaude) {
        this.id = id;
        this.nome = nome;
        this.tipoAnimal = tipoAnimal;
        this.genero = genero;
        this.idade = idade;
        this.dataNascimento = dataNascimento;
        this.raca = raca;
        this.problemasSaude = problemasSaude;
    }

    public static PetDTO converter(PetsModel petsModel) {
        return new PetDTO(
                petsModel.getId(),
                petsModel.getNome(),
                petsModel.getTipoAnimal(),
                petsModel.getGenero(),
                petsModel.getIdade(),
                petsModel.getDataNascimento(),
                petsModel.getRaca(),
                petsModel.getProblemasSaude()
        );
    }
}
