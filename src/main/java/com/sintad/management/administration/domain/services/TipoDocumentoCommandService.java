package com.sintad.management.administration.domain.services;

import com.sintad.management.administration.domain.model.aggregates.TipoDocumento;
import com.sintad.management.administration.domain.model.commands.CreateTipoDocumentoCommand;
import com.sintad.management.administration.domain.model.commands.DeleteTipoDocumentoCommand;
import com.sintad.management.administration.domain.model.commands.UpdateTipoDocumentoCommand;

import java.util.Optional;

public interface TipoDocumentoCommandService {
    Long handle(CreateTipoDocumentoCommand command);
    Optional<TipoDocumento> handle(UpdateTipoDocumentoCommand command);
    void handle(DeleteTipoDocumentoCommand command);
}
