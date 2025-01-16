package com.sintad.management.administration.application.internal.commandservices;

import com.sintad.management.administration.application.internal.commandservices.EntidadCommandServiceImpl;
import com.sintad.management.administration.domain.model.aggregates.Entidad;
import com.sintad.management.administration.domain.model.aggregates.TipoContribuyente;
import com.sintad.management.administration.domain.model.aggregates.TipoDocumento;
import com.sintad.management.administration.domain.model.commands.CreateEntidadCommand;
import com.sintad.management.administration.domain.model.commands.DeleteEntidadCommand;
import com.sintad.management.administration.domain.model.commands.UpdateEntidadCommand;
import com.sintad.management.administration.infrastructure.persistence.jpa.repositories.EntidadRepository;
import com.sintad.management.administration.infrastructure.persistence.jpa.repositories.TipoContribuyenteRepository;
import com.sintad.management.administration.infrastructure.persistence.jpa.repositories.TipoDocumentoRepository;
import com.sintad.management.shared.exception.DuplicateEntryException;
import com.sintad.management.shared.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntidadCommandServiceImplTest {

    @Mock
    private EntidadRepository entidadRepository;

    @Mock
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Mock
    private TipoContribuyenteRepository tipoContribuyenteRepository;

    @InjectMocks
    private EntidadCommandServiceImpl entidadCommandService;

    private CreateEntidadCommand createCommand;

    private UpdateEntidadCommand updateCommand;

    private DeleteEntidadCommand deleteCommand;

    @BeforeEach
    void setUp() {
        createCommand = new CreateEntidadCommand(
                1L,
                "123456789",
                "Razón Social Test",
                "Nombre Comercial Test",
                2L,
                "Dirección Test",
                "999999999",
                true
        );

        updateCommand = new UpdateEntidadCommand(
                1L,
                2L,
                "123456789",
                "Razón Social Test",
                "Nombre Comercial Actualizada",
                3L,
                "Dirección Actualizada",
                "999999999",
                true
        );

        deleteCommand = new DeleteEntidadCommand(1L);
    }

    @Test
    void shouldCreateEntidadWhenValid() {
        // Configurar mocks
        when(entidadRepository.existsByNroDocumento(createCommand.nroDocumento())).thenReturn(false);
        when(tipoDocumentoRepository.findById(createCommand.tipoDocumentoId())).thenReturn(Optional.of(mock(TipoDocumento.class)));
        when(tipoContribuyenteRepository.findById(createCommand.tipoContribuyenteId())).thenReturn(Optional.of(mock(TipoContribuyente.class)));
        when(entidadRepository.save(any(Entidad.class))).thenAnswer(invocation -> {
            Entidad entidad = invocation.getArgument(0);
            entidad.setId(1L);
            return entidad;
        });

        // Ejecutar el método
        Long result = entidadCommandService.handle(createCommand);

        // Verificar
        assertNotNull(result);
        assertEquals(1L, result);
        verify(entidadRepository, times(1)).save(any(Entidad.class));
    }



    @Test
    void shouldThrowDuplicateEntryExceptionWhenNroDocumentoExists() {
        // Configurar mocks
        when(entidadRepository.existsByNroDocumento(createCommand.nroDocumento())).thenReturn(true);

        // Ejecutar y verificar excepción
        assertThrows(DuplicateEntryException.class, () -> entidadCommandService.handle(createCommand));

        // Verificar que no se llame al método save
        verify(entidadRepository, never()).save(any());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenTipoDocumentoNotFound() {
        // Configurar mocks
        when(entidadRepository.existsByNroDocumento(createCommand.nroDocumento())).thenReturn(false);
        when(tipoDocumentoRepository.findById(createCommand.tipoDocumentoId())).thenReturn(Optional.empty());

        // Ejecutar y verificar excepción
        assertThrows(NotFoundException.class, () -> entidadCommandService.handle(createCommand));
    }

    @Test
    void shouldUpdateEntidadWhenValid() {
        // Configurar mocks
        Entidad existingEntidad = mock(Entidad.class);
        when(entidadRepository.findById(updateCommand.entidadId())).thenReturn(Optional.of(existingEntidad));
        when(tipoDocumentoRepository.findById(updateCommand.tipoDocumentoId())).thenReturn(Optional.of(mock(TipoDocumento.class)));
        when(tipoContribuyenteRepository.findById(updateCommand.tipoContribuyenteId())).thenReturn(Optional.of(mock(TipoContribuyente.class)));
        when(entidadRepository.save(any(Entidad.class))).thenReturn(existingEntidad);

        // Ejecutar
        Optional<Entidad> result = entidadCommandService.handle(updateCommand);

        // Verificar
        assertTrue(result.isPresent());
        verify(existingEntidad, times(1)).updateInformation(any(UpdateEntidadCommand.class), any(TipoDocumento.class), any(TipoContribuyente.class));
        verify(entidadRepository, times(1)).save(existingEntidad);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenEntidadToUpdateDoesNotExist() {
        // Configurar mocks
        when(entidadRepository.findById(updateCommand.entidadId())).thenReturn(Optional.empty());

        // Ejecutar y verificar excepción
        assertThrows(NotFoundException.class, () -> entidadCommandService.handle(updateCommand));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenTipoDocumentoDoesNotExistForUpdate() {
        // Configurar mocks
        when(entidadRepository.findById(updateCommand.entidadId())).thenReturn(Optional.of(mock(Entidad.class)));
        when(tipoDocumentoRepository.findById(updateCommand.tipoDocumentoId())).thenReturn(Optional.empty());

        // Ejecutar y verificar excepción
        assertThrows(NotFoundException.class, () -> entidadCommandService.handle(updateCommand));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenTipoContribuyenteDoesNotExistForUpdate() {
        // Configurar mocks
        when(entidadRepository.findById(updateCommand.entidadId())).thenReturn(Optional.of(mock(Entidad.class)));
        when(tipoDocumentoRepository.findById(updateCommand.tipoDocumentoId())).thenReturn(Optional.of(mock(TipoDocumento.class)));
        when(tipoContribuyenteRepository.findById(updateCommand.tipoContribuyenteId())).thenReturn(Optional.empty());

        // Ejecutar y verificar excepción
        assertThrows(NotFoundException.class, () -> entidadCommandService.handle(updateCommand));
    }

    @Test
    void shouldDeleteEntidadWhenValid() {
        // Configurar mocks
        when(entidadRepository.existsById(deleteCommand.entidadId())).thenReturn(true);

        // Ejecutar
        entidadCommandService.handle(deleteCommand);

        // Verificar
        verify(entidadRepository, times(1)).deleteById(deleteCommand.entidadId());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenEntidadToDeleteDoesNotExist() {
        // Configurar mocks
        when(entidadRepository.existsById(deleteCommand.entidadId())).thenReturn(false);

        // Ejecutar y verificar excepción
        assertThrows(NotFoundException.class, () -> entidadCommandService.handle(deleteCommand));

        // Verificar que no se llame a deleteById
        verify(entidadRepository, never()).deleteById(anyLong());
    }


}
