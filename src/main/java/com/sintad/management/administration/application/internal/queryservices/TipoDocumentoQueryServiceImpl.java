package com.sintad.management.administration.application.internal.queryservices;

import com.sintad.management.administration.domain.model.aggregates.TipoDocumento;
import com.sintad.management.administration.domain.model.queries.GetAllTipoDocumentosQuery;
import com.sintad.management.administration.domain.model.queries.GetTipoDocumentoByIdQuery;
import com.sintad.management.administration.domain.services.TipoDocumentoQueryService;
import com.sintad.management.administration.infrastructure.persistence.jpa.repositories.TipoDocumentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoDocumentoQueryServiceImpl implements TipoDocumentoQueryService {

    private final TipoDocumentoRepository tipoDocumentoRepository;

    public TipoDocumentoQueryServiceImpl(TipoDocumentoRepository tipoDocumentoRepository) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    @Override
    public Optional<TipoDocumento> handle(GetTipoDocumentoByIdQuery query) {
        return tipoDocumentoRepository.findById(query.tipoDocumentoId());
    }

    @Override
    public List<TipoDocumento> handle(GetAllTipoDocumentosQuery query) {
        return tipoDocumentoRepository.findAll();
    }
}
