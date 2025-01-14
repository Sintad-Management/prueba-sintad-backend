package com.sintad.management.administration.interfaces.rest;

import com.sintad.management.administration.domain.model.commands.DeleteTipoContribuyenteCommand;
import com.sintad.management.administration.domain.model.queries.GetAllTipoContribuyentesQuery;
import com.sintad.management.administration.domain.model.queries.GetTipoContribuyenteByIdQuery;
import com.sintad.management.administration.domain.services.TipoContribuyenteCommandService;
import com.sintad.management.administration.domain.services.TipoContribuyenteQueryService;
import com.sintad.management.administration.interfaces.rest.resources.tipoContribuyente.CreateTipoContribuyenteResource;
import com.sintad.management.administration.interfaces.rest.resources.tipoContribuyente.TipoContribuyenteResource;
import com.sintad.management.administration.interfaces.rest.resources.tipoContribuyente.UpdateTipoContribuyenteResource;
import com.sintad.management.administration.interfaces.rest.transform.tipoContribuyente.CreateTipoContribuyenteCommandFromResourceAssembler;
import com.sintad.management.administration.interfaces.rest.transform.tipoContribuyente.TipoContribuyenteResourceFromEntityAssembler;
import com.sintad.management.administration.interfaces.rest.transform.tipoContribuyente.UpdateTipoContribuyenteCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/tipo-contribuyentes", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Tipo Contribuyentes", description = "API de tipo contribuyentes")
public class TipoContribuyenteController {

    private final TipoContribuyenteCommandService tipoContribuyenteCommandService;
    private final TipoContribuyenteQueryService tipoContribuyenteQueryService;

    public TipoContribuyenteController(TipoContribuyenteCommandService tipoContribuyenteCommandService, TipoContribuyenteQueryService tipoContribuyenteQueryService) {
        this.tipoContribuyenteCommandService = tipoContribuyenteCommandService;
        this.tipoContribuyenteQueryService = tipoContribuyenteQueryService;
    }

    @Operation(summary = "Crear un tipo de contribuyente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo de contribuyente creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta"),
            @ApiResponse(responseCode = "404", description = "Tipo de contribuyente no encontrado")
    })
    @PostMapping
    public ResponseEntity<TipoContribuyenteResource> createTipoContribuyente(@RequestBody CreateTipoContribuyenteResource createTipoContribuyenteResource) {
        var createTipoContribuyenteCommand = CreateTipoContribuyenteCommandFromResourceAssembler.toCommandFromResource(createTipoContribuyenteResource);
        var tipoContribuyenteId = tipoContribuyenteCommandService.handle(createTipoContribuyenteCommand);
        if (tipoContribuyenteId == null || tipoContribuyenteId == 0L) return ResponseEntity.badRequest().build();
        var getTipoContribuyenteByIdQuery = new GetTipoContribuyenteByIdQuery(tipoContribuyenteId);
        var tipoContribuyente = tipoContribuyenteQueryService.handle(getTipoContribuyenteByIdQuery);
        if (tipoContribuyente.isEmpty()) return ResponseEntity.notFound().build();
        var tipoContribuyenteEntity = tipoContribuyente.get();
        var tipoContribuyenteResource = TipoContribuyenteResourceFromEntityAssembler.toResourceFromEntity(tipoContribuyenteEntity);
        return new ResponseEntity<>(tipoContribuyenteResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener un tipo de contribuyente por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de contribuyente encontrado"),
            @ApiResponse(responseCode = "404", description = "Tipo de contribuyente no encontrado")
    })
    @GetMapping("/{tipoContribuyenteId}")
    public ResponseEntity<TipoContribuyenteResource> getTipoContribuyenteById(@PathVariable Long tipoContribuyenteId) {
        var getTipoContribuyenteByIdQuery = new GetTipoContribuyenteByIdQuery(tipoContribuyenteId);
        var tipoContribuyente = tipoContribuyenteQueryService.handle(getTipoContribuyenteByIdQuery);
        if (tipoContribuyente.isEmpty()) return ResponseEntity.notFound().build();
        var tipoContribuyenteEntity = tipoContribuyente.get();
        var tipoContribuyenteResource = TipoContribuyenteResourceFromEntityAssembler.toResourceFromEntity(tipoContribuyenteEntity);
        return ResponseEntity.ok(tipoContribuyenteResource);
    }

    @Operation(summary = "Obtener todos los tipos de contribuyentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipos de contribuyentes encontrados"),
            @ApiResponse(responseCode = "404", description = "Tipos de contribuyentes no encontrados")
    })
    @GetMapping
    public ResponseEntity<List<TipoContribuyenteResource>> getAllTipoContribuyentes() {
        var getAllTipoContribuyentesQuery = new GetAllTipoContribuyentesQuery();
        var tipoContribuyentes = tipoContribuyenteQueryService.handle(getAllTipoContribuyentesQuery);
        var tipoContribuyenteResources = tipoContribuyentes.stream()
                .map(TipoContribuyenteResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(tipoContribuyenteResources);
    }

    @Operation(summary = "Actualizar un tipo de contribuyente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de contribuyente actualizado"),
            @ApiResponse(responseCode = "404", description = "Tipo de contribuyente no encontrado")
    })
    @PutMapping("/{tipoContribuyenteId}")
    public ResponseEntity<TipoContribuyenteResource> updateTipoContribuyente(@PathVariable Long tipoContribuyenteId, @RequestBody UpdateTipoContribuyenteResource updateTipoContribuyenteResource) {
        var updateTipoContribuyenteCommand = UpdateTipoContribuyenteCommandFromResourceAssembler.toCommandFromResource(tipoContribuyenteId, updateTipoContribuyenteResource);
        var updatedTipoContribuyente = tipoContribuyenteCommandService.handle(updateTipoContribuyenteCommand);
        if (updatedTipoContribuyente.isEmpty()) return ResponseEntity.notFound().build();
        var updatedTipoContribuyenteEntity = updatedTipoContribuyente.get();
        var updatedTipoContribuyenteResource = TipoContribuyenteResourceFromEntityAssembler.toResourceFromEntity(updatedTipoContribuyenteEntity);
        return ResponseEntity.ok(updatedTipoContribuyenteResource);
    }

    @Operation(summary = "Eliminar un tipo de contribuyente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de contribuyente eliminado"),
            @ApiResponse(responseCode = "404", description = "Tipo de contribuyente no encontrado")
    })
    @DeleteMapping("/{tipoContribuyenteId}")
    public ResponseEntity<?> deleteTipoContribuyente(@PathVariable Long tipoContribuyenteId) {
        var deleteTipoContribuyenteCommand = new DeleteTipoContribuyenteCommand(tipoContribuyenteId);
        tipoContribuyenteCommandService.handle(deleteTipoContribuyenteCommand);
        return ResponseEntity.ok("Tipo de contribuyente eliminado exitosamente");
    }
}