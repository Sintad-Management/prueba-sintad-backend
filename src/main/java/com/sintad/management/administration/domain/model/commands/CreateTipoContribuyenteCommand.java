package com.sintad.management.administration.domain.model.commands;

public record CreateTipoContribuyenteCommand(
    String nombre,
    Boolean estado
) {
    public CreateTipoContribuyenteCommand {
            if (nombre == null || nombre.isBlank()) {
                throw new IllegalArgumentException("nombre cannot be null or empty");
            }
            if (estado == null) {
                throw new IllegalArgumentException("estado cannot be null");
            }
        }
    }