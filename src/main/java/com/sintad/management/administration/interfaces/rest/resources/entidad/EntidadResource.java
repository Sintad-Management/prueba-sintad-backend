package com.sintad.management.administration.interfaces.rest.resources.entidad;

public record EntidadResource(
        Long idEntidad,
        String razonSocial,
        String nombreComercial,
        TipoSimplificadoResource tipoDocumento,
        String nroDocumento,
        TipoSimplificadoResource tipoContribuyente,
        String direccion,
        String telefono,
        Boolean estado
) {
}