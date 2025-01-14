package com.sintad.management.administration.domain.services;

import com.sintad.management.administration.domain.model.aggregates.TipoDocumento;
import com.sintad.management.administration.domain.model.queries.GetAllTipoDocumentosQuery;
import com.sintad.management.administration.domain.model.queries.GetTipoDocumentoByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TipoDocumentoQueryService {
    Optional<TipoDocumento> handle(GetTipoDocumentoByIdQuery query);
    List<TipoDocumento> handle(GetAllTipoDocumentosQuery query);
}
