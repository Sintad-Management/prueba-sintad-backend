package com.sintad.management.administration.domain.model.commands;

public record DeleteTipoContribuyenteCommand(Long tipoContribuyenteId) {
    public DeleteTipoContribuyenteCommand {
        if (tipoContribuyenteId == null) {
            throw new IllegalArgumentException("tipoContribuyenteId cannot be null");
        }
    }
}
