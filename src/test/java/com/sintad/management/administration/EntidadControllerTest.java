package com.sintad.management.administration;

import com.sintad.management.administration.domain.services.EntidadCommandService;
import com.sintad.management.administration.domain.services.EntidadQueryService;
import com.sintad.management.administration.interfaces.rest.EntidadController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EntidadControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EntidadCommandService entidadCommandService;

    @Mock
    private EntidadQueryService entidadQueryService;

    @InjectMocks
    private EntidadController entidadController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(entidadController).build();
    }

    @Test
    void shouldDeleteEntidad() throws Exception {
        mockMvc.perform(delete("/api/v1/entidades/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}