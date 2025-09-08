package br.com.petpoints.back.features.doutores.model;

import br.com.petpoints.back.features.doutores.enums.CargoMedicoEnum;
import br.com.petpoints.back.features.usuario.model.UsuarioModel;
import br.com.petpoints.back.shared.classes.Pessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doutores")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DoutoresModel extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;

    private CargoMedicoEnum especializacao;
}
