package com.sintad.management.administration.interfaces.rest.transform.tipoContribuyente;

import com.sintad.management.administration.domain.model.aggregates.TipoContribuyente;
import com.sintad.management.administration.interfaces.rest.resources.tipoContribuyente.TipoContribuyenteResource;

public class TipoContribuyenteResourceFromEntityAssembler {
public static TipoContribuyenteResource toResourceFromEntity(TipoContribuyente entity) {
        return new TipoContribuyenteResource(
                entity.getId(),
                entity.getNombre(),
                entity.getEstado()
        );
    }
}
