package com.sintad.management.administration.application.internal.queryservices;

import com.sintad.management.administration.application.internal.queryservices.EntidadQueryServiceImpl;
import com.sintad.management.administration.domain.model.aggregates.Entidad;
import com.sintad.management.administration.domain.model.queries.GetAllEntidadesQuery;
import com.sintad.management.administration.domain.model.queries.GetEntidadByIdQuery;
import com.sintad.management.administration.infrastructure.persistence.jpa.repositories.EntidadRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntidadQueryServiceImplTest {

    @Mock
    private EntidadRepository entidadRepository;

    @InjectMocks
    private EntidadQueryServiceImpl entidadQueryService;

    private GetEntidadByIdQuery getEntidadByIdQuery;
    private GetAllEntidadesQuery getAllEntidadesQuery;

    @BeforeEach
    void setUp() {
        getEntidadByIdQuery = new GetEntidadByIdQuery(1L);
        getAllEntidadesQuery = new GetAllEntidadesQuery();
    }

    @Test
    void shouldReturnEntidadWhenFoundById() {
        Entidad entidad = new Entidad();
        when(entidadRepository.findById(getEntidadByIdQuery.entidadId())).thenReturn(Optional.of(entidad));

        Optional<Entidad> result = entidadQueryService.handle(getEntidadByIdQuery);

        assertTrue(result.isPresent());
        assertEquals(entidad, result.get());
        verify(entidadRepository, times(1)).findById(getEntidadByIdQuery.entidadId());
    }

    @Test
    void shouldReturnEmptyWhenEntidadNotFoundById() {
        when(entidadRepository.findById(getEntidadByIdQuery.entidadId())).thenReturn(Optional.empty());

        Optional<Entidad> result = entidadQueryService.handle(getEntidadByIdQuery);

        assertFalse(result.isPresent());
        verify(entidadRepository, times(1)).findById(getEntidadByIdQuery.entidadId());
    }

    @Test
    void shouldReturnAllEntidades() {
        Entidad entidad = new Entidad();
        when(entidadRepository.findAll()).thenReturn(Collections.singletonList(entidad));

        List<Entidad> result = entidadQueryService.handle(getAllEntidadesQuery);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(entidad, result.get(0));
        verify(entidadRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnEmptyListWhenNoEntidadesFound() {
        when(entidadRepository.findAll()).thenReturn(Collections.emptyList());

        List<Entidad> result = entidadQueryService.handle(getAllEntidadesQuery);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(entidadRepository, times(1)).findAll();
    }
}