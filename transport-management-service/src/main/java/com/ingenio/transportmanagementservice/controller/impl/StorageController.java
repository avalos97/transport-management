package com.ingenio.transportmanagementservice.controller.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.accepted;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import com.ingenio.transportmanagementservice.controller.IStorageController;
import com.ingenio.transportmanagementservice.dto.StorageDto;
import com.ingenio.transportmanagementservice.dto.StorageTypeDto;
import com.ingenio.transportmanagementservice.service.IStorageService;
import com.ingenio.transportmanagementservice.service.IStorageTypeService;

@RestController
public class StorageController implements IStorageController {

    private final IStorageService service;
    private final IStorageTypeService typeService;

    public StorageController(IStorageService service, IStorageTypeService typeService) {
        this.service = service;
        this.typeService = typeService;
    }

    @Override
    public ResponseEntity<StorageDto> createStorage(@Valid StorageDto req) {
        return status(HttpStatus.CREATED).body(service.save(req));
    }

    @Override
    public ResponseEntity<Void> deleteStorage(Long id) {
        service.delete(id);
        return accepted().build();
    }

    @Override
    public ResponseEntity<List<StorageDto>> findAll() {
        return ok(service.getAll());
    }

    @Override
    public ResponseEntity<StorageDto> getStorageById(Long id) {
        return ok(service.get(id));
    }

    @Override
    public ResponseEntity<StorageDto> updateStorage(Long id, @Valid StorageDto req) {
        return ok(service.update(id, req));
    }

    @Override
    public ResponseEntity<List<StorageTypeDto>> findAllTypes() {
        return ok(typeService.findAll());
    }

}
