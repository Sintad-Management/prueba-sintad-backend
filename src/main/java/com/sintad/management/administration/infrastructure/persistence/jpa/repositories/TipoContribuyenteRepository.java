package com.sintad.management.administration.infrastructure.persistence.jpa.repositories;

import com.sintad.management.administration.domain.model.aggregates.TipoContribuyente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoContribuyenteRepository extends JpaRepository<TipoContribuyente, Long> {
}
