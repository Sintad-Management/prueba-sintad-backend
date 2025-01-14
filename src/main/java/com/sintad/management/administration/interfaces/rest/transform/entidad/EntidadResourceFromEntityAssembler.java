package com.sintad.management.administration.interfaces.rest.transform.entidad;

import com.sintad.management.administration.domain.model.aggregates.Entidad;
import com.sintad.management.administration.interfaces.rest.resources.entidad.EntidadResource;
import com.sintad.management.administration.interfaces.rest.resources.entidad.TipoSimplificadoResource;

public class EntidadResourceFromEntityAssembler {
    public static EntidadResource toResourceFromEntity(Entidad entity) {
        return new EntidadResource(
                entity.getId(),
                entity.getRazonSocial(),
                entity.getNombreComercial(),
                new TipoSimplificadoResource(
                        entity.getTipoDocumento().getId(),
                        entity.getTipoDocumento().getNombre()
                ),
                entity.getNroDocumento(),
                new TipoSimplificadoResource(
                        entity.getTipoContribuyente().getId(),
                        entity.getTipoContribuyente().getNombre()
                ),
                entity.getDireccion(),
                entity.getTelefono(),
                entity.getEstado()
        );
    }
}