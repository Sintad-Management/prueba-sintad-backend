package com.sintad.management.administration.domain.model.commands;

public record UpdateTipoDocumentoCommand(
        Long tipoDocumentoId,
        String codigo,
        String nombre,
        String descripcion,
        Boolean estado
) {
    public UpdateTipoDocumentoCommand {
        if (tipoDocumentoId == null) {
            throw new IllegalArgumentException("tipoDocumentoId cannot be null");
        }
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("codigo cannot be null or empty");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("nombre cannot be null or empty");
        }
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("descripcion cannot be null or empty");
        }
        if (estado == null) {
            throw new IllegalArgumentException("estado cannot be null");
        }
    }
}
