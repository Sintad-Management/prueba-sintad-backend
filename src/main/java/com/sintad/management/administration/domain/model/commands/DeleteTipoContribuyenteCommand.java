package com.sintad.management.administration.domain.model.commands;

public record DeleteTipoContribuyenteCommand(Long tipoContribuyenteId) {
    public DeleteTipoContribuyenteCommand {
        if (tipoContribuyenteId == null || tipoContribuyenteId <= 0) {
            throw new IllegalArgumentException("tipoContribuyenteId cannot be null or <= 0");
        }
    }
}