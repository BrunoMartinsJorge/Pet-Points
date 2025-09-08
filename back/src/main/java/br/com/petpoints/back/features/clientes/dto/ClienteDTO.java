package br.com.petpoints.back.features.clientes.dto;

import br.com.petpoints.back.features.clientes.model.ClienteModel;
import br.com.petpoints.back.shared.classes.Pessoa;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClienteDTO extends Pessoa {

    private Long id;

    public ClienteDTO(Long id) {
        this.id = id;
    }

    public static ClienteDTO converter(ClienteModel clienteModel) {
        return new ClienteDTO(
                clienteModel.getId()
        );
    }
}
