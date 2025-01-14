package com.sintad.management.administration.interfaces.rest.transform.tipoContribuyente;

import com.sintad.management.administration.domain.model.commands.CreateTipoContribuyenteCommand;
import com.sintad.management.administration.interfaces.rest.resources.tipoContribuyente.CreateTipoContribuyenteResource;

public class CreateTipoContribuyenteCommandFromResourceAssembler {
    public static CreateTipoContribuyenteCommand toCommandFromResource(CreateTipoContribuyenteResource resource) {
        return new CreateTipoContribuyenteCommand(
                resource.nombre(),
                resource.estado()
        );
    }
}
