package com.sintad.management.administration.interfaces.rest.transform.entidad;

import com.sintad.management.administration.domain.model.commands.UpdateEntidadCommand;
import com.sintad.management.administration.interfaces.rest.resources.entidad.UpdateEntidadResource;

public class UpdateEntidadCommandFromResourceAssembler {
    public static UpdateEntidadCommand toCommandFromResource(Long id, UpdateEntidadResource resource) {
        return new UpdateEntidadCommand(
                id,
                resource.tipoDocumentoId(),
                resource.nroDocumento(),
                resource.razonSocial(),
                resource.nombreComercial(),
                resource.tipoContribuyenteId(),
                resource.direccion(),
                resource.telefono(),
                resource.estado()
        );
    }
}