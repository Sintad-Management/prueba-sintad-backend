package com.sintad.management.administration.interfaces.rest;

import com.sintad.management.administration.domain.model.commands.CreateTipoDocumentoCommand;
import com.sintad.management.administration.domain.model.commands.DeleteTipoDocumentoCommand;
import com.sintad.management.administration.domain.model.commands.UpdateTipoDocumentoCommand;
import com.sintad.management.administration.domain.model.queries.GetAllTipoDocumentosQuery;
import com.sintad.management.administration.domain.model.queries.GetTipoDocumentoByIdQuery;
import com.sintad.management.administration.domain.services.TipoDocumentoCommandService;
import com.sintad.management.administration.domain.services.TipoDocumentoQueryService;
import com.sintad.management.administration.interfaces.rest.resources.tipoDocumento.CreateTipoDocumentoResource;
import com.sintad.management.administration.interfaces.rest.resources.tipoDocumento.TipoDocumentoResource;
import com.sintad.management.administration.interfaces.rest.resources.tipoDocumento.UpdateTipoDocumentoResource;
import com.sintad.management.administration.interfaces.rest.transform.tipoDocumento.CreateTipoDocumentoCommandFromResourceAssembler;
import com.sintad.management.administration.interfaces.rest.transform.tipoDocumento.TipoDocumentoResourceFromEntityAssembler;
import com.sintad.management.administration.interfaces.rest.transform.tipoDocumento.UpdateTipoDocumentoCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/tipo-documentos", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Tipo Documentos", description = "API de tipo documentos")
public class TipoDocumentoController {

    private final TipoDocumentoCommandService tipoDocumentoCommandService;
    private final TipoDocumentoQueryService tipoDocumentoQueryService;

    public TipoDocumentoController(TipoDocumentoCommandService tipoDocumentoCommandService, TipoDocumentoQueryService tipoDocumentoQueryService) {
        this.tipoDocumentoCommandService = tipoDocumentoCommandService;
        this.tipoDocumentoQueryService = tipoDocumentoQueryService;
    }

    @Operation(summary = "Crear un tipo de documento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo de documento creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "404", description = "Tipo de documento no encontrado")
    })
    @PostMapping
    public ResponseEntity<TipoDocumentoResource> createTipoDocumento(@RequestBody CreateTipoDocumentoResource createTipoDocumentoResource) {
        var createTipoDocumentoCommand = CreateTipoDocumentoCommandFromResourceAssembler.toCommandFromResource(createTipoDocumentoResource);
        var tipoDocumentoId = tipoDocumentoCommandService.handle(createTipoDocumentoCommand);
        if (tipoDocumentoId == null || tipoDocumentoId == 0L) return ResponseEntity.badRequest().build();
        var getTipoDocumentoByIdQuery = new GetTipoDocumentoByIdQuery(tipoDocumentoId);
        var tipoDocumento = tipoDocumentoQueryService.handle(getTipoDocumentoByIdQuery);
        if (tipoDocumento.isEmpty()) return ResponseEntity.notFound().build();
        var tipoDocumentoEntity = tipoDocumento.get();
        var tipoDocumentoResource = TipoDocumentoResourceFromEntityAssembler.toResourceFromEntity(tipoDocumentoEntity);
        return new ResponseEntity<>(tipoDocumentoResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener un tipo de documento por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de documento encontrado"),
            @ApiResponse(responseCode = "404", description = "Tipo de documento no encontrado")
    })
    @GetMapping("/{tipoDocumentoId}")
    public ResponseEntity<TipoDocumentoResource> getTipoDocumentoById(@PathVariable Long tipoDocumentoId) {
        var getTipoDocumentoByIdQuery = new GetTipoDocumentoByIdQuery(tipoDocumentoId);
        var tipoDocumento = tipoDocumentoQueryService.handle(getTipoDocumentoByIdQuery);
        if (tipoDocumento.isEmpty()) return ResponseEntity.notFound().build();
        var tipoDocumentoEntity = tipoDocumento.get();
        var tipoDocumentoResource = TipoDocumentoResourceFromEntityAssembler.toResourceFromEntity(tipoDocumentoEntity);
        return ResponseEntity.ok(tipoDocumentoResource);
    }

    @Operation(summary = "Obtener todos los tipos de documentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipos de documentos encontrados"),
            @ApiResponse(responseCode = "404", description = "Tipos de documentos no encontrados")
    })
    @GetMapping
    public ResponseEntity<List<TipoDocumentoResource>> getAllTipoDocumentos() {
        var getAllTipoDocumentosQuery = new GetAllTipoDocumentosQuery();
        var tipoDocumentos = tipoDocumentoQueryService.handle(getAllTipoDocumentosQuery);
        var tipoDocumentoResources = tipoDocumentos.stream()
                .map(TipoDocumentoResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(tipoDocumentoResources);
    }
    
    @Operation(summary = "Actualizar un tipo de documento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de documento actualizado"),
            @ApiResponse(responseCode = "404", description = "Tipo de documento no encontrado")
    })
    @PutMapping("/{tipoDocumentoId}")
    public ResponseEntity<TipoDocumentoResource> updateTipoDocumento(@PathVariable Long tipoDocumentoId, @RequestBody UpdateTipoDocumentoResource updateTipoDocumentoResource) {
        var updateTipoDocumentoCommand = UpdateTipoDocumentoCommandFromResourceAssembler.toCommandFromResource(tipoDocumentoId, updateTipoDocumentoResource);
        var updatedTipoDocumento = tipoDocumentoCommandService.handle(updateTipoDocumentoCommand);
        if (updatedTipoDocumento.isEmpty()) return ResponseEntity.notFound().build();
        var updatedTipoDocumentoEntity = updatedTipoDocumento.get();
        var updatedTipoDocumentoResource = TipoDocumentoResourceFromEntityAssembler.toResourceFromEntity(updatedTipoDocumentoEntity);
        return ResponseEntity.ok(updatedTipoDocumentoResource);
    }

    @Operation(summary = "Eliminar un tipo de documento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de documento eliminado"),
            @ApiResponse(responseCode = "404", description = "Tipo de documento no encontrado")
    })
    @DeleteMapping("/{tipoDocumentoId}")
    public ResponseEntity<?> deleteTipoDocumento(@PathVariable Long tipoDocumentoId) {
        var deleteTipoDocumentoCommand = new DeleteTipoDocumentoCommand(tipoDocumentoId);
        tipoDocumentoCommandService.handle(deleteTipoDocumentoCommand);
        return ResponseEntity.ok("Tipo de documento eliminado exitosamente");
    }
}