package com.sintad.management.administration.domain.model.aggregates;

import com.sintad.management.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_tipo_contribuyente")
public class TipoContribuyente extends AuditableAbstractAggregateRoot<TipoContribuyente> {

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "estado", nullable = false)
    private Boolean estado;
}
