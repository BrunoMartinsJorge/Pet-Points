package br.com.petpoints.back.features.doutores.service;

import br.com.petpoints.back.features.doutores.repository.DoutorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoutoresServiceImpl implements DoutoresService{

    private final DoutorRepository doutorRepository;

    public DoutoresServiceImpl(DoutorRepository doutorRepository) {
        this.doutorRepository = doutorRepository;
    }
}
