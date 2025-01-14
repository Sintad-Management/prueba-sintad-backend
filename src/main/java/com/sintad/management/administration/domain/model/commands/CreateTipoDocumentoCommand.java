package com.sintad.management.administration.domain.model.commands;

public record CreateTipoDocumentoCommand(
        String codigo,
        String nombre,
        String descripcion,
        Boolean estado
) {
    public CreateTipoDocumentoCommand {
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
