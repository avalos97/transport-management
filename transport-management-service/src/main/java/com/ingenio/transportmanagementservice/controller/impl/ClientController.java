package com.ingenio.transportmanagementservice.controller.impl;

import static org.springframework.http.ResponseEntity.accepted;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.ingenio.transportmanagementservice.controller.IClientController;
import com.ingenio.transportmanagementservice.dto.ClientDto;
import com.ingenio.transportmanagementservice.service.IClientService;

@Validated
@RestController
public class ClientController implements IClientController {

    private final IClientService service;

    public ClientController(IClientService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<ClientDto> createClient(@Valid ClientDto req) {
        return status(HttpStatus.CREATED).body(service.save(req));
    }

    @Override
    public ResponseEntity<Void> deleteClient(Long id) {
        service.delete(id);
        return accepted().build();
    }

    @Override
    public ResponseEntity<List<ClientDto>> findAll() {
        return ok(service.getAll());
    }

    @Override
    public ResponseEntity<ClientDto> getClientById(Long id) {
        return ok(service.get(id));
    }

    @Override
    public ResponseEntity<ClientDto> updateClient(Long id, @Valid ClientDto req) {
        return ok(service.update(id, req));
    }

}
