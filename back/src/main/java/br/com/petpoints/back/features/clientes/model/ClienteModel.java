package br.com.petpoints.back.features.clientes.model;

import br.com.petpoints.back.features.usuario.model.UsuarioModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;

    @OneToMany(mappedBy = "dono", cascade = CascadeType.ALL, orphanRemoval = true)
    //Mapeando a partir da entidade Pet no campo 'dono'
    private List<PetsModel> pets = new ArrayList<>();

    public ClienteModel(UsuarioModel usuario) {
        this.usuario = usuario;
    }
}
