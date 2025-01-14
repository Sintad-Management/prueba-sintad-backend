package com.sintad.management.administration.interfaces.rest.transform.entidad;

import com.sintad.management.administration.domain.model.commands.CreateEntidadCommand;
import com.sintad.management.administration.interfaces.rest.resources.entidad.CreateEntidadResource;

public class CreateEntidadCommandFromResourceAssembler {
    public static CreateEntidadCommand toCommandFromResource(CreateEntidadResource resource) {
        return new CreateEntidadCommand(
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