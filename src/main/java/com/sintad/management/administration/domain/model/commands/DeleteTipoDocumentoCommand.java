package com.sintad.management.administration.domain.model.commands;

public record DeleteTipoDocumentoCommand(Long tipoDocumentoId) {
    public DeleteTipoDocumentoCommand {
        if (tipoDocumentoId == null || tipoDocumentoId <= 0) {
            throw new IllegalArgumentException("tipoDocumentoId cannot be null or <= 0");
        }
    }
}
