package com.sintad.management.administration.application.internal.queryservices;

import com.sintad.management.administration.domain.model.aggregates.Entidad;
import com.sintad.management.administration.domain.model.queries.GetAllEntidadesQuery;
import com.sintad.management.administration.domain.model.queries.GetEntidadByIdQuery;
import com.sintad.management.administration.domain.services.EntidadQueryService;
import com.sintad.management.administration.infrastructure.persistence.jpa.repositories.EntidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntidadQueryServiceImpl implements EntidadQueryService {

    private final EntidadRepository entidadRepository;

    public EntidadQueryServiceImpl(EntidadRepository entidadRepository) {
        this.entidadRepository = entidadRepository;
    }

    @Override
    public Optional<Entidad> handle(GetEntidadByIdQuery query) {
        return entidadRepository.findById(query.entidadId());
    }

    @Override
    public List<Entidad> handle(GetAllEntidadesQuery query) {
        return entidadRepository.findAll();
    }
}
