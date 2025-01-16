package com.sintad.management.administration.infrastructure.persistence.jpa.repositories;

import com.sintad.management.administration.domain.model.aggregates.Entidad;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class EntidadRepositoryTest {

    @Autowired
    private EntidadRepository entidadRepository;

    @Test
    void shouldSaveAndFindEntidadById() {
        Entidad entidad = new Entidad();
        entidad.setNroDocumento("123456789");
        entidad.setRazonSocial("Razón Social Test");
        entidad.setNombreComercial("Nombre Comercial Test");
        entidad.setDireccion("Dirección Test");
        entidad.setTelefono("999999999");
        entidad.setEstado(true);

        entidad = entidadRepository.save(entidad);

        Optional<Entidad> foundEntidad = entidadRepository.findById(entidad.getId());

        assertTrue(foundEntidad.isPresent());
        assertEquals(entidad.getNroDocumento(), foundEntidad.get().getNroDocumento());
    }

    @Test
    void shouldReturnEmptyWhenEntidadNotFoundById() {
        Optional<Entidad> foundEntidad = entidadRepository.findById(999L);

        assertFalse(foundEntidad.isPresent());
    }
}