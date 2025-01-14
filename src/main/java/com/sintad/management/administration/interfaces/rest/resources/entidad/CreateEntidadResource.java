package com.sintad.management.administration.interfaces.rest.resources.entidad;

public record CreateEntidadResource(
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
