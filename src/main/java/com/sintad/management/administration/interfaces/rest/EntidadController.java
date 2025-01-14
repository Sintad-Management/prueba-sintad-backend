package com.sintad.management.administration.interfaces.rest;

import com.sintad.management.administration.domain.model.commands.DeleteEntidadCommand;
import com.sintad.management.administration.domain.model.queries.GetAllEntidadesQuery;
import com.sintad.management.administration.domain.model.queries.GetEntidadByIdQuery;
import com.sintad.management.administration.domain.services.EntidadCommandService;
import com.sintad.management.administration.domain.services.EntidadQueryService;
import com.sintad.management.administration.interfaces.rest.resources.entidad.CreateEntidadResource;
import com.sintad.management.administration.interfaces.rest.resources.entidad.EntidadResource;
import com.sintad.management.administration.interfaces.rest.resources.entidad.UpdateEntidadResource;
import com.sintad.management.administration.interfaces.rest.transform.entidad.CreateEntidadCommandFromResourceAssembler;
import com.sintad.management.administration.interfaces.rest.transform.entidad.EntidadResourceFromEntityAssembler;
import com.sintad.management.administration.interfaces.rest.transform.entidad.UpdateEntidadCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/entidades", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Entidades", description = "API de entidades")
public class EntidadController {

    private final EntidadCommandService entidadCommandService;
    private final EntidadQueryService entidadQueryService;

    public EntidadController(EntidadCommandService entidadCommandService, EntidadQueryService entidadQueryService) {
        this.entidadCommandService = entidadCommandService;
        this.entidadQueryService = entidadQueryService;
    }

    @Operation(summary = "Crear una entidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Entidad creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "404", description = "Entidad no encontrada")
    })
    @PostMapping
    public ResponseEntity<EntidadResource> createEntidad(@RequestBody CreateEntidadResource createEntidadResource) {
        var createEntidadCommand = CreateEntidadCommandFromResourceAssembler.toCommandFromResource(createEntidadResource);
        var entidadId = entidadCommandService.handle(createEntidadCommand);
        if (entidadId == null || entidadId == 0L) return ResponseEntity.badRequest().build();
        var getEntidadByIdQuery = new GetEntidadByIdQuery(entidadId);
        var entidad = entidadQueryService.handle(getEntidadByIdQuery);
        if (entidad.isEmpty()) return ResponseEntity.notFound().build();
        var entidadEntity = entidad.get();
        var entidadResource = EntidadResourceFromEntityAssembler.toResourceFromEntity(entidadEntity);
        return new ResponseEntity<>(entidadResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener una entidad por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entidad encontrada"),
            @ApiResponse(responseCode = "404", description = "Entidad no encontrada")
    })
    @GetMapping("/{entidadId}")
    public ResponseEntity<EntidadResource> getEntidadById(@PathVariable Long entidadId) {
        var getEntidadByIdQuery = new GetEntidadByIdQuery(entidadId);
        var entidad = entidadQueryService.handle(getEntidadByIdQuery);
        if (entidad.isEmpty()) return ResponseEntity.notFound().build();
        var entidadEntity = entidad.get();
        var entidadResource = EntidadResourceFromEntityAssembler.toResourceFromEntity(entidadEntity);
        return ResponseEntity.ok(entidadResource);
    }

    @Operation(summary = "Obtener todas las entidades")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entidades encontradas"),
            @ApiResponse(responseCode = "404", description = "Entidades no encontradas")
    })
    @GetMapping
    public ResponseEntity<List<EntidadResource>> getAllEntidades() {
        var getAllEntidadesQuery = new GetAllEntidadesQuery();
        var entidades = entidadQueryService.handle(getAllEntidadesQuery);
        var entidadResources = entidades.stream()
                .map(EntidadResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(entidadResources);
    }

    @Operation(summary = "Actualizar una entidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entidad actualizada"),
            @ApiResponse(responseCode = "404", description = "Entidad no encontrada")
    })
    @PutMapping("/{entidadId}")
    public ResponseEntity<EntidadResource> updateEntidad(@PathVariable Long entidadId, @RequestBody UpdateEntidadResource updateEntidadResource) {
        var updateEntidadCommand = UpdateEntidadCommandFromResourceAssembler.toCommandFromResource(entidadId, updateEntidadResource);
        var updatedEntidad = entidadCommandService.handle(updateEntidadCommand);
        if (updatedEntidad.isEmpty()) return ResponseEntity.notFound().build();
        var updatedEntidadEntity = updatedEntidad.get();
        var updatedEntidadResource = EntidadResourceFromEntityAssembler.toResourceFromEntity(updatedEntidadEntity);
        return ResponseEntity.ok(updatedEntidadResource);
    }

    @Operation(summary = "Eliminar una entidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entidad eliminada"),
            @ApiResponse(responseCode = "404", description = "Entidad no encontrada")
    })
    @DeleteMapping("/{entidadId}")
    public ResponseEntity<?> deleteEntidad(@PathVariable Long entidadId) {
        var deleteEntidadCommand = new DeleteEntidadCommand(entidadId);
        entidadCommandService.handle(deleteEntidadCommand);
        return ResponseEntity.ok("Entidad eliminada exitosamente");
    }
}