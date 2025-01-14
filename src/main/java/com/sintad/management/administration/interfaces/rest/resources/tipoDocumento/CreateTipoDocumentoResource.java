package com.sintad.management.administration.interfaces.rest.resources.tipoDocumento;

public record CreateTipoDocumentoResource(
        String codigo,
        String nombre,
        String descripcion,
        Boolean estado
) {
}
