package com.sintad.management.administration.infrastructure.persistence.jpa.repositories;

import com.sintad.management.administration.domain.model.aggregates.Entidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntidadRepository extends JpaRepository<Entidad,Long> {
    boolean existsByNroDocumento(String nroDocumento);
}
