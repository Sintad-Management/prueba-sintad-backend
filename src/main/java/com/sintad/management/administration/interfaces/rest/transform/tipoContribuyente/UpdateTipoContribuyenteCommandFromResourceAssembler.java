package com.sintad.management.administration.interfaces.rest.transform.tipoContribuyente;

import com.sintad.management.administration.domain.model.commands.UpdateTipoContribuyenteCommand;
import com.sintad.management.administration.interfaces.rest.resources.tipoContribuyente.UpdateTipoContribuyenteResource;

public class UpdateTipoContribuyenteCommandFromResourceAssembler {
    public static UpdateTipoContribuyenteCommand toCommandFromResource(UpdateTipoContribuyenteResource resource) {
        return new UpdateTipoContribuyenteCommand(
                resource.id(),
                resource.nombre(),
                resource.estado()
        );
    }
}
