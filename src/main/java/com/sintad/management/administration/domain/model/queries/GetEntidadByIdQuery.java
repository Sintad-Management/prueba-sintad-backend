package com.sintad.management.administration.domain.model.queries;

public record GetEntidadByIdQuery(Long entidadId) {
    public GetEntidadByIdQuery {
        if (entidadId == null || entidadId <= 0) {
            throw new IllegalArgumentException("entidadId cannot be null or <= 0");
        }
    }
}
