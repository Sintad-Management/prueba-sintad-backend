package com.sintad.management.administration.interfaces.rest.resources.tipoDocumento;

public record UpdateTipoDocumentoResource(
        String codigo,
        String nombre,
        String descripcion,
        Boolean estado
) {
}
