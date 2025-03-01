package com.sintad.management.administration.application.internal.commandservices;

import com.sintad.management.administration.domain.model.aggregates.TipoContribuyente;
import com.sintad.management.administration.domain.model.commands.CreateTipoContribuyenteCommand;
import com.sintad.management.administration.domain.model.commands.DeleteTipoContribuyenteCommand;
import com.sintad.management.administration.domain.model.commands.UpdateTipoContribuyenteCommand;
import com.sintad.management.administration.domain.services.TipoContribuyenteCommandService;
import com.sintad.management.administration.infrastructure.persistence.jpa.repositories.TipoContribuyenteRepository;
import com.sintad.management.shared.exception.NotFoundException;
import com.sintad.management.shared.exception.TipoContribuyenteDeletionException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoContribuyenteCommandServiceImpl implements TipoContribuyenteCommandService {

    private final TipoContribuyenteRepository tipoContribuyenteRepository;

    public TipoContribuyenteCommandServiceImpl(TipoContribuyenteRepository tipoContribuyenteRepository) {
        this.tipoContribuyenteRepository = tipoContribuyenteRepository;
    }

    @Override
    public Long handle(CreateTipoContribuyenteCommand command) {
        TipoContribuyente tipoContribuyente = new TipoContribuyente(command);
        tipoContribuyente = tipoContribuyenteRepository.save(tipoContribuyente);
        return tipoContribuyente.getId();
    }

    @Override
    public Optional<TipoContribuyente> handle(UpdateTipoContribuyenteCommand command) {
        Optional<TipoContribuyente> optionalTipoContribuyente = tipoContribuyenteRepository.findById(command.tipoContribuyenteId());
        if (optionalTipoContribuyente.isPresent()) {
            TipoContribuyente tipoContribuyente = optionalTipoContribuyente.get();
            tipoContribuyente.updateInformation(command.nombre(), command.estado());
            tipoContribuyente = tipoContribuyenteRepository.save(tipoContribuyente);
            return Optional.of(tipoContribuyente);
        } else {
            throw new NotFoundException("Id de tipo de contribuyente %s no encontrado".formatted(command.tipoContribuyenteId()));
        }
    }

    @Override
    public void handle(DeleteTipoContribuyenteCommand command) {
        if (!tipoContribuyenteRepository.existsById(command.tipoContribuyenteId())) {
            throw new NotFoundException("Id de tipo de contribuyente %s no existe".formatted(command.tipoContribuyenteId()));
        }
        try {
            tipoContribuyenteRepository.deleteById(command.tipoContribuyenteId());
        } catch (Exception e) {
            throw new TipoContribuyenteDeletionException("Error al eliminar el tipo de contribuyente: %s".formatted(e.getMessage()));
        }
    }
}