package br.com.petpoints.back.features.doutores.repository;

import br.com.petpoints.back.features.doutores.model.DoutoresModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoutorRepository extends JpaRepository<DoutoresModel, Long> {
}
