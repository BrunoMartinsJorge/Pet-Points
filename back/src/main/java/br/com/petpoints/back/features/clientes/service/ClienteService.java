package br.com.petpoints.back.features.clientes.service;

import br.com.petpoints.back.features.clientes.dto.PetDTO;
import br.com.petpoints.back.features.clientes.forms.PetsForms;
import br.com.petpoints.back.features.clientes.model.PetsModel;
import br.com.petpoints.back.features.clientes.repository.ClienteRepository;
import br.com.petpoints.back.features.clientes.repository.PetsRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PetsRepository petsRepository;

    public ClienteService(ClienteRepository clienteRepository, PetsRepository petsRepository) {
        this.clienteRepository = clienteRepository;
        this.petsRepository = petsRepository;
    }

    public PetDTO cadastrarNovoPet(PetsForms pet, Long idDono) {
        PetsModel petsModel = new PetsModel();
        //ClienteModel dono = clienteRepository.findById(idDono).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        petsModel.setNome(pet.getNome());
        petsModel.setIdade(pet.getIdade());
        petsModel.setGenero(pet.getGenero());
        petsModel.setDataNascimento(pet.getDataNascimento());
        petsModel.setProblemasSaude(pet.getProblemasSaude());
        petsModel.setRaca(pet.getRaca());
        petsModel.setTipoAnimal(pet.getTipoAnimal());
        petsModel.setDono(null);
        petsRepository.save(petsModel);
        return PetDTO.converter(petsModel);
    }
}
