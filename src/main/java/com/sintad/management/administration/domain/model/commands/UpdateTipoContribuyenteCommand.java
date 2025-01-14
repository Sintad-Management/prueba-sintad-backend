package com.sintad.management.administration.domain.model.commands;

public record UpdateTipoContribuyenteCommand(
        Long tipoContribuyenteId,
        String nombre,
        Boolean estado
) {
    public UpdateTipoContribuyenteCommand {
        if (tipoContribuyenteId == null) {
            throw new IllegalArgumentException("tipoContribuyenteId cannot be null");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("nombre cannot be null or empty");
        }
        if (estado == null) {
            throw new IllegalArgumentException("estado cannot be null");
        }
    }
}
