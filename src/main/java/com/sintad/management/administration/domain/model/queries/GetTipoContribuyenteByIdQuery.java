package com.sintad.management.administration.domain.model.queries;

public record GetTipoContribuyenteByIdQuery(Long tipoContribuyenteId) {
    public GetTipoContribuyenteByIdQuery {
        if (tipoContribuyenteId == null) {
            throw new IllegalArgumentException("tipoContribuyenteId cannot be null");
        }
    }
}
