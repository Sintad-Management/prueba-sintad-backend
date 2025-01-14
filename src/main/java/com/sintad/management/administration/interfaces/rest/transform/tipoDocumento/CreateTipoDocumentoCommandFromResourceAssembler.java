package com.sintad.management.administration.interfaces.rest.transform.tipoDocumento;

import com.sintad.management.administration.domain.model.commands.CreateTipoDocumentoCommand;
import com.sintad.management.administration.interfaces.rest.resources.tipoDocumento.CreateTipoDocumentoResource;

public class CreateTipoDocumentoCommandFromResourceAssembler {
    public static CreateTipoDocumentoCommand toCommandFromResource(CreateTipoDocumentoResource resource) {
        return new CreateTipoDocumentoCommand(
                resource.codigo(),
                resource.nombre(),
                resource.descripcion(),
                resource.estado()
        );
    }
}