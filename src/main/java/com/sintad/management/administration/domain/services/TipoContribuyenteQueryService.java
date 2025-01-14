package com.sintad.management.administration.domain.services;

import com.sintad.management.administration.domain.model.aggregates.TipoContribuyente;
import com.sintad.management.administration.domain.model.queries.GetAllTipoContribuyentesQuery;
import com.sintad.management.administration.domain.model.queries.GetTipoContribuyenteByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TipoContribuyenteQueryService {
    Optional<TipoContribuyente> handle(GetTipoContribuyenteByIdQuery query);
    List<TipoContribuyente> handle(GetAllTipoContribuyentesQuery query);

}
