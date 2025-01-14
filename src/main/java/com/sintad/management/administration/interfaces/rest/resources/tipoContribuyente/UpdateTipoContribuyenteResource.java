package com.sintad.management.administration.interfaces.rest.resources.tipoContribuyente;

public record UpdateTipoContribuyenteResource(
        Long id,
        String nombre,
        Boolean estado
) {
}
