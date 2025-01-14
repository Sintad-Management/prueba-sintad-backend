package com.sintad.management.administration.domain.model.commands;

public record DeleteEntidadCommand(Long entidadId) {
    public DeleteEntidadCommand {
        if (entidadId == null || entidadId <= 0) {
            throw new IllegalArgumentException("entidadId cannot be null or <= 0");
        }
    }
}
