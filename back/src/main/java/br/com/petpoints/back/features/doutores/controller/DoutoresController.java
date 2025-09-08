package br.com.petpoints.back.features.doutores.controller;

import br.com.petpoints.back.features.doutores.service.DoutoresServiceImpl;
import br.com.petpoints.back.shared.annotations.seguranca.FeatureParaDoutores;
import org.springframework.web.bind.annotation.RequestMapping;

@FeatureParaDoutores
@RequestMapping("/doutores")
public class DoutoresController {

    private final DoutoresServiceImpl doutoresService;

    public DoutoresController(DoutoresServiceImpl doutoresService) {
        this.doutoresService = doutoresService;
    }
}
