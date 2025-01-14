package com.sintad.management.administration.application.internal.queryservices;

import com.sintad.management.administration.domain.model.aggregates.TipoContribuyente;
import com.sintad.management.administration.domain.model.queries.GetAllTipoContribuyentesQuery;
import com.sintad.management.administration.domain.model.queries.GetTipoContribuyenteByIdQuery;
import com.sintad.management.administration.domain.services.TipoContribuyenteQueryService;
import com.sintad.management.administration.infrastructure.persistence.jpa.repositories.TipoContribuyenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoContribuyenteQueryServiceImpl implements TipoContribuyenteQueryService {

    private final TipoContribuyenteRepository tipoContribuyenteRepository;

    public TipoContribuyenteQueryServiceImpl(TipoContribuyenteRepository tipoContribuyenteRepository) {
        this.tipoContribuyenteRepository = tipoContribuyenteRepository;
    }

    @Override
    public Optional<TipoContribuyente> handle(GetTipoContribuyenteByIdQuery query) {
        return tipoContribuyenteRepository.findById(query.tipoContribuyenteId());
    }

    @Override
    public List<TipoContribuyente> handle(GetAllTipoContribuyentesQuery query) {
        return tipoContribuyenteRepository.findAll();
    }
}
