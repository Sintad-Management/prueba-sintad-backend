package com.sintad.management.administration.application.internal.commandservices;

import com.sintad.management.administration.domain.model.aggregates.Entidad;
import com.sintad.management.administration.domain.model.commands.CreateEntidadCommand;
import com.sintad.management.administration.domain.model.commands.DeleteEntidadCommand;
import com.sintad.management.administration.domain.model.commands.UpdateEntidadCommand;
import com.sintad.management.administration.domain.services.EntidadCommandService;
import com.sintad.management.administration.infrastructure.persistence.jpa.repositories.EntidadRepository;
import com.sintad.management.administration.infrastructure.persistence.jpa.repositories.TipoContribuyenteRepository;
import com.sintad.management.administration.infrastructure.persistence.jpa.repositories.TipoDocumentoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntidadCommandServiceImpl implements EntidadCommandService {

    private final EntidadRepository entidadRepository;
    private final TipoContribuyenteRepository tipoContribuyenteRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;

    public EntidadCommandServiceImpl(EntidadRepository entidadRepository, TipoContribuyenteRepository tipoContribuyenteRepository, TipoDocumentoRepository tipoDocumentoRepository) {
        this.entidadRepository = entidadRepository;
        this.tipoContribuyenteRepository = tipoContribuyenteRepository;
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    @Override
    public Long handle(CreateEntidadCommand command) {
        var tipoDocumento = tipoDocumentoRepository.findById(command.tipoDocumentoId())
                .orElseThrow(() -> new IllegalArgumentException("TipoDocumento con ID %d no encontrado".formatted(command.tipoDocumentoId())));

        var tipoContribuyente = tipoContribuyenteRepository.findById(command.tipoContribuyenteId())
                .orElseThrow(() -> new IllegalArgumentException("TipoContribuyente con ID %d no encontrado".formatted(command.tipoContribuyenteId())));

        var entidad = new Entidad(command, tipoDocumento, tipoContribuyente);

        entidad = entidadRepository.save(entidad);
        return entidad.getId();
    }

    @Override
    public Optional<Entidad> handle(UpdateEntidadCommand command) {

        var entidad = entidadRepository.findById(command.entidadId())
                .orElseThrow(() -> new IllegalArgumentException("Entidad con ID %d no encontrada".formatted(command.entidadId())));

        var tipoDocumento = tipoDocumentoRepository.findById(command.tipoDocumentoId())
                .orElseThrow(() -> new IllegalArgumentException("TipoDocumento con ID %d no encontrado".formatted(command.tipoDocumentoId())));

        var tipoContribuyente = tipoContribuyenteRepository.findById(command.tipoContribuyenteId())
                .orElseThrow(() -> new IllegalArgumentException("TipoContribuyente con ID %d no encontrado".formatted(command.tipoContribuyenteId())));

        entidad.updateInformation(command, tipoDocumento, tipoContribuyente);

        entidad = entidadRepository.save(entidad);

        return Optional.of(entidad);
    }
    @Override
    public void handle(DeleteEntidadCommand command) {
        if (!entidadRepository.existsById(command.entidadId())) {
            throw new IllegalArgumentException("Id de entidad %s no encontrado".formatted(command.entidadId()));
        }
        entidadRepository.deleteById(command.entidadId());
    }
}
