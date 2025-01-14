package com.sintad.management.administration.interfaces.rest.resources.tipoDocumento;

public record TipoDocumentoResource(
        Long id,
        String codigo,
        String nombre,
        String descripcion,
        Boolean estado
) {
}
