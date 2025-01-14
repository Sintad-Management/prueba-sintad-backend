package com.sintad.management.administration.domain.services;

import com.sintad.management.administration.domain.model.aggregates.Entidad;
import com.sintad.management.administration.domain.model.commands.CreateEntidadCommand;
import com.sintad.management.administration.domain.model.commands.DeleteEntidadCommand;
import com.sintad.management.administration.domain.model.commands.UpdateEntidadCommand;

import java.util.Optional;

public interface EntidadCommandService {
    Long handle(CreateEntidadCommand command);
    Optional<Entidad> handle(UpdateEntidadCommand command);
    void handle(DeleteEntidadCommand command);
}
