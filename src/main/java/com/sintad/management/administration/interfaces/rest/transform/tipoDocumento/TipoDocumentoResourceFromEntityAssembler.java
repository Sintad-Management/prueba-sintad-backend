package com.sintad.management.administration.interfaces.rest.transform.tipoDocumento;

import com.sintad.management.administration.domain.model.aggregates.TipoDocumento;
import com.sintad.management.administration.interfaces.rest.resources.tipoDocumento.TipoDocumentoResource;

public class TipoDocumentoResourceFromEntityAssembler {
    public static TipoDocumentoResource toResourceFromEntity(TipoDocumento entity) {
        return new TipoDocumentoResource(
                entity.getId(),
                entity.getCodigo(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getEstado()
        );
    }
}