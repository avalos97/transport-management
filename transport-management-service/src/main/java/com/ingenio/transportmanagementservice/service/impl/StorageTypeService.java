package com.ingenio.transportmanagementservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ingenio.transportmanagementservice.component.util.BaseMapper;
import com.ingenio.transportmanagementservice.domain.entity.StorageType;
import com.ingenio.transportmanagementservice.domain.repository.StorageTypeRepository;
import com.ingenio.transportmanagementservice.dto.StorageTypeDto;
import com.ingenio.transportmanagementservice.service.IStorageTypeService;

@Service
public class StorageTypeService extends BaseMapper<StorageType, StorageTypeDto> implements IStorageTypeService {

    private final StorageTypeRepository repository;

    public StorageTypeService(StorageTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StorageTypeDto> findAll() {
        return this.entityListToDtoList(repository.findAll());
    }

    @Override
    protected Class<StorageType> getEntityClass() {
        return StorageType.class;
    }

    @Override
    protected Class<StorageTypeDto> getDtoClass() {
        return StorageTypeDto.class;
    }

}
