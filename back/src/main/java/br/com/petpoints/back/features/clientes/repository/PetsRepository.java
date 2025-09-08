package br.com.petpoints.back.features.clientes.repository;

import br.com.petpoints.back.features.clientes.model.PetsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetsRepository extends JpaRepository<PetsModel, Long> {
}
