package com.sintad.management.administration.domain.model.aggregates;

import com.sintad.management.administration.domain.model.commands.CreateTipoDocumentoCommand;
import com.sintad.management.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity
public class TipoDocumento extends AuditableAbstractAggregateRoot<TipoDocumento> {

    @Column(name = "codigo", length = 20, nullable = false)
    private String codigo;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 255, nullable = true)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    public TipoDocumento(CreateTipoDocumentoCommand command) {
        this.codigo = command.codigo();
        this.nombre = command.nombre();
        this.descripcion = command.descripcion();
        this.estado = command.estado();
    }

    public TipoDocumento updateInformation(String codigo, String nombre, String descripcion, Boolean estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        return this;
    }
}
