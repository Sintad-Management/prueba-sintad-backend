package com.sintad.management.administration.infrastructure.persistence.jpa.repositories;

import com.sintad.management.administration.domain.model.aggregates.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {
}
