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
@Table(name = "tb_tipo_documento")
public class TipoDocumento extends AuditableAbstractAggregateRoot<TipoDocumento> {

    @Column(name = "codigo", length = 20, nullable = false)
    private String codigo;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 255, nullable = true)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;
}
