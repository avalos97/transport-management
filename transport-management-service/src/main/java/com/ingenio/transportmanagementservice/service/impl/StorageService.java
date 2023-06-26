package com.ingenio.transportmanagementservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ingenio.transportmanagementservice.component.exception.customExceptions.RecordNotFoundException;
import com.ingenio.transportmanagementservice.component.util.BaseMapper;
import com.ingenio.transportmanagementservice.domain.entity.Storage;
import com.ingenio.transportmanagementservice.domain.repository.StorageRepository;
import com.ingenio.transportmanagementservice.dto.StorageDto;
import com.ingenio.transportmanagementservice.service.IStorageService;

@Service
public class StorageService extends BaseMapper<Storage, StorageDto> implements IStorageService {

    private final StorageRepository repository;

    public StorageService(StorageRepository repository) {
        this.repository = repository;
    }

    @Override
    public StorageDto save(StorageDto dto) {
        Storage entity = repository.save(this.dtoToEntity(dto));
        return this.entityToDto(entity);
    }

    @Override
    public StorageDto update(Long id, StorageDto dto) {
        this.get(id); // * validamos que exista el cliente */
        Storage entity = this.dtoToEntity(dto);
        return this.entityToDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        this.get(id); // * validamos que exista el cliente */
        repository.deleteById(id);
    }

    @Override
    public StorageDto get(Long id) {
        Storage entity = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        return this.entityToDto(entity);
    }

    @Override
    public List<StorageDto> getAll() {
        return this.entityListToDtoList(repository.findAll());
    }

    @Override
    protected Class<Storage> getEntityClass() {
        return Storage.class;
    }

    @Override
    protected Class<StorageDto> getDtoClass() {
        return StorageDto.class;
    }

}
