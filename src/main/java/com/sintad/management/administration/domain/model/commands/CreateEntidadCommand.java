package com.sintad.management.administration.domain.model.commands;

public record CreateEntidadCommand(
        Long tipoDocumentoId,
        String nroDocumento,
        String razonSocial,
        String nombreComercial,
        Long tipoContribuyenteId,
        String direccion,
        String telefono,
        Boolean estado
) {
    public CreateEntidadCommand {
        if (tipoDocumentoId == null) {
            throw new IllegalArgumentException("tipoDocumentoId cannot be null");
        }
        if (nroDocumento == null || nroDocumento.isBlank()) {
            throw new IllegalArgumentException("nroDocumento cannot be null or empty");
        }
        if (razonSocial == null || razonSocial.isBlank()) {
            throw new IllegalArgumentException("razonSocial cannot be null or empty");
        }
        if (tipoContribuyenteId == null) {
            throw new IllegalArgumentException("tipoContribuyenteId cannot be null");
        }
        if (direccion == null || direccion.isBlank()) {
            throw new IllegalArgumentException("direccion cannot be null or empty");
        }
        if (estado == null) {
            throw new IllegalArgumentException("estado cannot be null");
        }
    }
}
