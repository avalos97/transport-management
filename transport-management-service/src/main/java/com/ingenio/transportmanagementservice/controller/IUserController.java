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

import com.ingenio.transportmanagementservice.dto.UserDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Validated
@RequestMapping("/api/v1/users")
public interface IUserController {
    
        @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Request has succeded", content = @Content(schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @PostMapping(produces = { "application/json" })
    default ResponseEntity<UserDto> createUser(@Valid @RequestBody(required = true) UserDto req) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Update a user give an Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request has succeded", content = @Content(schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @PutMapping(value = "/{id}", produces = { "application/json" })
    default ResponseEntity<UserDto> updateUser(
            @Parameter(description = "Customer Identifier", required = true) @PathVariable("id") Long id,
            @Valid @RequestBody(required = true) UserDto req) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Delete a user give an Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Request has succeded", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @DeleteMapping(value = "/{id}", produces = { "application/json" })
    default ResponseEntity<Void> deleteUser(
            @Parameter(description = "Customer Identifier", required = true) @PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))),
            @ApiResponse(responseCode = "404", description = "Users not found", content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @GetMapping(produces = { "application/json" })
    default ResponseEntity<List<UserDto>> findAll() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Operation(summary = "Get a user by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @GetMapping(value = "/{id}", produces = { "application/json" })
    default ResponseEntity<UserDto> getUserById(
            @Parameter(description = "ID of the user to be obtained. Cannot be empty.") @PathVariable("id") Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
