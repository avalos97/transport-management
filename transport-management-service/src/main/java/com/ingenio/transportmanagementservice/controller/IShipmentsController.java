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
import org.springframework.web.bind.annotation.RequestParam;

import com.ingenio.transportmanagementservice.dto.ShipmentDto;
import com.ingenio.transportmanagementservice.dto.ShippingTypeDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Validated
@RequestMapping("/api/v1/shipments")
public interface IShipmentsController {

    @Operation(summary = "Create a new shipment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Shipment created successfully", content = @Content(schema = @Schema(implementation = ShipmentDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    })
    @PostMapping(produces = { "application/json" })
    default ResponseEntity<ShipmentDto> createShipment(@Valid @RequestBody ShipmentDto shipmentDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Update an existing shipment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shipment updated successfully", content = @Content(schema = @Schema(implementation = ShipmentDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    })
    @PutMapping(value = "/{id}", produces = { "application/json" })
    default ResponseEntity<ShipmentDto> updateShipment(@PathVariable("id") Long id,
            @Valid @RequestBody ShipmentDto shipmentDto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Delete a shipment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Shipment deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Shipment not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    default ResponseEntity<Void> deleteShipment(@PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Find all shipments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = ShipmentDto.class))),
    })
    @GetMapping(produces = { "application/json" })
    default ResponseEntity<List<ShipmentDto>> findAll() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Get a shipment by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = ShipmentDto.class))),
            @ApiResponse(responseCode = "404", description = "Shipment not found", content = @Content)
    })
    @GetMapping(value = "/{id}", produces = { "application/json" })
    default ResponseEntity<ShipmentDto> getShipmentById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Search shipments based on dynamic query")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = ShipmentDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    })
    @GetMapping(value = "/search", produces = { "application/json" })
    default ResponseEntity<List<ShipmentDto>> searchShipments(
            @Parameter(description = "field by which the search is performed") @RequestParam(value = "key") String key,
            @Parameter(description = "is the operation to perform (for example, equal to, greater than, less than, etc.)") @RequestParam(value = "operation") String operation,
            @Parameter(description = "is the value that is sought") @RequestParam(value = "value") String value) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Find all shipping types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = ShippingTypeDto.class))),
    })
    @GetMapping(value = "/types", produces = { "application/json" })
    default ResponseEntity<List<ShippingTypeDto>> findAllTypes() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
