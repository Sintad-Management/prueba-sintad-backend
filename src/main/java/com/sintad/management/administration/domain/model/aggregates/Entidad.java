package com.sintad.management.administration.domain.model.aggregates;

import com.sintad.management.administration.domain.model.commands.CreateEntidadCommand;
import com.sintad.management.administration.domain.model.commands.UpdateEntidadCommand;
import com.sintad.management.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_entidad")
@Entity
public class Entidad extends AuditableAbstractAggregateRoot<Entidad> {

    @ManyToOne
    @JoinColumn(name = "id_tipo_documento", nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "nro_documento", length = 20, nullable = false)
    private String nroDocumento;

    @Column(name = "razon_social", length = 255, nullable = false)
    private String razonSocial;

    @Column(name = "nombre_comercial", length = 255, nullable = true)
    private String nombreComercial;

    @ManyToOne
    @JoinColumn(name = "id_tipo_contribuyente", nullable = false)
    private TipoContribuyente tipoContribuyente;

    @Column(name = "direccion", length = 255, nullable = false)
    private String direccion;

    @Column(name = "telefono", length = 50, nullable = true)
    private String telefono;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    public Entidad(CreateEntidadCommand command, TipoDocumento tipoDocumento, TipoContribuyente tipoContribuyente) {
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = command.nroDocumento();
        this.razonSocial = command.razonSocial();
        this.nombreComercial = command.nombreComercial();
        this.tipoContribuyente = tipoContribuyente;
        this.direccion = command.direccion();
        this.telefono = command.telefono();
        this.estado = command.estado();
    }

    public Entidad updateInformation(UpdateEntidadCommand command, TipoDocumento tipoDocumento, TipoContribuyente tipoContribuyente) {
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = command.nroDocumento();
        this.razonSocial = command.razonSocial();
        this.nombreComercial = command.nombreComercial();
        this.tipoContribuyente = tipoContribuyente;
        this.direccion = command.direccion();
        this.telefono = command.telefono();
        this.estado = command.estado();
        return this;
    }
}
