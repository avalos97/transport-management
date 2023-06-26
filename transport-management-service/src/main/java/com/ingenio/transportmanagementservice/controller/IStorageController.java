package com.ingenio.transportmanagementservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ingenio.transportmanagementservice.dto.StorageDto;
import com.ingenio.transportmanagementservice.dto.StorageTypeDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Validated
@RequestMapping("/api/v1/storage")
public interface IStorageController {

        @Operation(summary = "Create a new storage")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Storage created successfully", content = @Content(schema = @Schema(implementation = StorageDto.class))),
                        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = Void.class)))
        })
        @PostMapping(produces = { "application/json" })
        default ResponseEntity<StorageDto> createStorage(@Valid @RequestBody(required = true) StorageDto req) {
                return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

        @Operation(summary = "Update an existing storage")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = StorageDto.class))),
                        @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = Void.class))),
                        @ApiResponse(responseCode = "404", description = "Storage not found", content = @Content(schema = @Schema(implementation = Void.class)))
        })
        @PutMapping(value = "/{id}", produces = { "application/json" })
        default ResponseEntity<StorageDto> updateStorage(
                        @Parameter(description = "ID of the storage to be updated", required = true) @PathVariable("id") Long id,
                        @Valid @RequestBody(required = true) StorageDto req) {
                return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

        @Operation(summary = "Delete a storage")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Void.class))),
                        @ApiResponse(responseCode = "404", description = "Storage not found", content = @Content(schema = @Schema(implementation = Void.class)))
        })
        @DeleteMapping(value = "/{id}")
        default ResponseEntity<Void> deleteStorage(
                        @Parameter(description = "ID of the storage to be deleted", required = true) @PathVariable("id") Long id) {
                return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

        @Operation(summary = "Get a list of all storages")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = StorageDto.class))) })
        })
        @GetMapping(produces = { "application/json" })
        default ResponseEntity<List<StorageDto>> findAll() {
                return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

        @Operation(summary = "Find storage by ID")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = StorageDto.class))),
                        @ApiResponse(responseCode = "404", description = "Storage not found", content = @Content(schema = @Schema(implementation = Void.class)))
        })
        @GetMapping(value = "/{id}", produces = { "application/json" })
        default ResponseEntity<StorageDto> getStorageById(
                        @Parameter(description = "ID of storage to be fetched", required = true) @PathVariable("id") Long id) {
                return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

        @Operation(summary = "Find all storages types")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = StorageTypeDto.class))),
        })
        @GetMapping(value = "/types", produces = { "application/json" })
        default ResponseEntity<List<StorageTypeDto>> findAllTypes() {
                return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

}
