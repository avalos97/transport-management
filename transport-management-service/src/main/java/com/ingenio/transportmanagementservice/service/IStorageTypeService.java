package com.ingenio.transportmanagementservice.service;

import java.util.List;

import com.ingenio.transportmanagementservice.dto.StorageTypeDto;

public interface IStorageTypeService {
    
    List<StorageTypeDto> findAll();
}
