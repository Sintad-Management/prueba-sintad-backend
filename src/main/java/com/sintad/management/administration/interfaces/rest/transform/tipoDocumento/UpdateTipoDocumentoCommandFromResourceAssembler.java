package com.sintad.management.administration.interfaces.rest.transform.tipoDocumento;
import com.sintad.management.administration.domain.model.commands.UpdateTipoDocumentoCommand;
import com.sintad.management.administration.interfaces.rest.resources.tipoDocumento.UpdateTipoDocumentoResource;

public class UpdateTipoDocumentoCommandFromResourceAssembler {
    public static UpdateTipoDocumentoCommand toCommandFromResource(UpdateTipoDocumentoResource resource) {
        return new UpdateTipoDocumentoCommand(
                resource.id(),
                resource.codigo(),
                resource.nombre(),
                resource.descripcion(),
                resource.estado()
        );
    }
}