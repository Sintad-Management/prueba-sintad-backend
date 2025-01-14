package com.sintad.management.administration.application.internal.commandservices;

import com.sintad.management.administration.domain.model.aggregates.TipoContribuyente;
import com.sintad.management.administration.domain.model.commands.CreateTipoContribuyenteCommand;
import com.sintad.management.administration.domain.model.commands.DeleteTipoContribuyenteCommand;
import com.sintad.management.administration.domain.model.commands.UpdateTipoContribuyenteCommand;
import com.sintad.management.administration.domain.services.TipoContribuyenteCommandService;
import com.sintad.management.administration.infrastructure.persistence.jpa.repositories.TipoContribuyenteRepository;
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
        TipoContribuyente tipoContribuyente = new TipoContribuyente();
        tipoContribuyente.setNombre(command.nombre());
        tipoContribuyente.setEstado(command.estado());
        tipoContribuyente = tipoContribuyenteRepository.save(tipoContribuyente);
        return tipoContribuyente.getId();
    }

    @Override
    public Optional<TipoContribuyente> handle(UpdateTipoContribuyenteCommand command) {
        Optional<TipoContribuyente> optionalTipoContribuyente = tipoContribuyenteRepository.findById(command.tipoContribuyenteId());
        if (optionalTipoContribuyente.isPresent()) {
            TipoContribuyente tipoContribuyente = optionalTipoContribuyente.get();
            tipoContribuyente.setNombre(command.nombre());
            tipoContribuyente.setEstado(command.estado());
            tipoContribuyente = tipoContribuyenteRepository.save(tipoContribuyente);
            return Optional.of(tipoContribuyente);
        } else {
            throw new IllegalArgumentException("Id de tipo de contribuyente %s no encontrado".formatted(command.tipoContribuyenteId()));
        }
    }

    @Override
    public void handle(DeleteTipoContribuyenteCommand command) {
        if (!tipoContribuyenteRepository.existsById(command.tipoContribuyenteId())) {
            throw new IllegalArgumentException("Id de tipo de contribuyente %s no existe".formatted(command.tipoContribuyenteId()));
        }
        try {
            tipoContribuyenteRepository.deleteById(command.tipoContribuyenteId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al eliminar el tipo de contribuyente: %s".formatted(e.getMessage()));
        }
    }
}