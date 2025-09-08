package br.com.petpoints.back.features.clientes.model;

import br.com.petpoints.back.shared.enums.GeneroEnum;
import br.com.petpoints.back.shared.enums.PetEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo 'nome' não pode ser nulo!")
    private String nome;

    @Column(name = "tipo_animal")
    @NotNull(message = "O campo 'tipo_animal' não pode ser nulo!")
    @Enumerated(EnumType.STRING)
    private PetEnum tipoAnimal;

    @NotNull(message = "O campo 'genero' não pode ser nulo!")
    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;

    @NotNull(message = "O campo 'idade' não pode ser nulo!")
    private int idade;

    @Column(name = "data_nascimento")
    @NotNull(message = "O campo 'data_nascimento' não pode ser nulo!")
    private LocalDate dataNascimento;

    @Column(name = "data_cadastro")
    @CreationTimestamp
    private LocalDateTime dataCadastro;

    @NotNull(message = "O campo 'raca' não pode ser nulo!")
    private String raca;

    @ManyToOne
    @JoinColumn(name = "cliente_id") //O nome da coluna que deve ser utilizada com FK
    private ClienteModel dono;

    @Column(name = "problemas_saude")
    private String problemasSaude;
}
