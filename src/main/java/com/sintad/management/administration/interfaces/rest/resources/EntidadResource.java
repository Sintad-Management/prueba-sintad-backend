package com.sintad.management.administration.interfaces.rest.resources;

public record EntidadResource(
        Long id,
        Long tipoDocumentoId,
        String nroDocumento,
        String razonSocial,
        String nombreComercial,
        Long tipoContribuyenteId,
        String direccion,
        String telefono,
        Boolean estado
) {
}
