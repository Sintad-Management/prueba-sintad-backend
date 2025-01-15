package com.sintad.management.administration.application.internal.commandservices;

import com.sintad.management.administration.domain.model.aggregates.TipoDocumento;
import com.sintad.management.administration.domain.model.commands.CreateTipoDocumentoCommand;
import com.sintad.management.administration.domain.model.commands.DeleteTipoDocumentoCommand;
import com.sintad.management.administration.domain.model.commands.UpdateTipoDocumentoCommand;
import com.sintad.management.administration.domain.services.TipoDocumentoCommandService;
import com.sintad.management.administration.infrastructure.persistence.jpa.repositories.TipoDocumentoRepository;
import com.sintad.management.shared.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoDocumentoCommandServiceImpl implements TipoDocumentoCommandService {

    private final TipoDocumentoRepository tipoDocumentoRepository;

    public TipoDocumentoCommandServiceImpl(TipoDocumentoRepository tipoDocumentoRepository) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    @Override
    public Long handle(CreateTipoDocumentoCommand command) {
        TipoDocumento tipoDocumento = new TipoDocumento(command);
        tipoDocumento = tipoDocumentoRepository.save(tipoDocumento);
        return tipoDocumento.getId();
    }

    @Override
    public Optional<TipoDocumento> handle(UpdateTipoDocumentoCommand command) {
        Optional<TipoDocumento> optionalTipoDocumento = tipoDocumentoRepository.findById(command.tipoDocumentoId());
        if (optionalTipoDocumento.isPresent()) {
            TipoDocumento tipoDocumento = optionalTipoDocumento.get();
            tipoDocumento.updateInformation(command.codigo(), command.nombre(), command.descripcion(), command.estado());
            tipoDocumento = tipoDocumentoRepository.save(tipoDocumento);
            return Optional.of(tipoDocumento);
        } else {
            throw new NotFoundException("Id de tipo de documento %s no encontrado".formatted(command.tipoDocumentoId()));
        }
    }

    @Override
    public void handle(DeleteTipoDocumentoCommand command) {
        if (!tipoDocumentoRepository.existsById(command.tipoDocumentoId())) {
            throw new NotFoundException("Id de tipo de documento %s no encontrado".formatted(command.tipoDocumentoId()));
        }
        tipoDocumentoRepository.deleteById(command.tipoDocumentoId());
    }
}