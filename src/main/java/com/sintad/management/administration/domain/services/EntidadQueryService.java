package com.sintad.management.administration.domain.services;

import com.sintad.management.administration.domain.model.aggregates.Entidad;
import com.sintad.management.administration.domain.model.queries.GetAllEntidadesQuery;
import com.sintad.management.administration.domain.model.queries.GetEntidadByIdQuery;

import java.util.List;
import java.util.Optional;

public interface EntidadQueryService{
    Optional<Entidad> handle(GetEntidadByIdQuery query);
    List<Entidad> handle(GetAllEntidadesQuery query);
}
