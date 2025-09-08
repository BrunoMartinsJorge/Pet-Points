package br.com.petpoints.back.features.gerentes.model;

import br.com.petpoints.back.features.usuario.model.UsuarioModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gerentes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GerenteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;
}
