package br.com.petpoints.back.features.doutores.enums;

import lombok.NoArgsConstructor;

public enum CargoMedicoEnum {
    CLINICO_GERAL("Clínico Geral"),
    CIRURGIAO("Cirurgião"),
    DERMATOLOGISTA("Dermatologista"),
    NUTRICIONISTA("Nutricionista"),
    CARDIOLOGISTA("Cardiologista"),
    ONCOLOGISTA("Oncologista"),
    ORTOPEDISTA("Ortopedista"),
    OFTALMOLOGISTA("Oftalmologista"),
    NEUROLOGISTA("Neurologista"),
    ANESTESISTA("Anestesista"),
    IMAGENOLOGISTA("Imagem / Diagnóstico por Imagem"),
    ENDOSCOPISTA("Endoscopista"),
    REPRODUCAO_ANIMAL("Reprodução Animal"),
    COMPORTAMENTAL("Especialista em Comportamento Animal"),
    EXOTICOS("Especialista em Animais Exóticos"),
    ODONTOLOGISTA("Odontologista");

    private final String descricao;

    CargoMedicoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}