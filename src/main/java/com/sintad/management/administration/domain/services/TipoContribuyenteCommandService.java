package com.sintad.management.administration.domain.services;

import com.sintad.management.administration.domain.model.aggregates.TipoContribuyente;
import com.sintad.management.administration.domain.model.commands.CreateTipoContribuyenteCommand;
import com.sintad.management.administration.domain.model.commands.DeleteTipoContribuyenteCommand;
import com.sintad.management.administration.domain.model.commands.UpdateTipoContribuyenteCommand;

import java.util.Optional;

public interface TipoContribuyenteCommandService {
    Long handle(CreateTipoContribuyenteCommand command);
    Optional<TipoContribuyente> handle(UpdateTipoContribuyenteCommand command);
    void handle(DeleteTipoContribuyenteCommand command);
}
