package com.ingenio.transportmanagementservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ingenio.transportmanagementservice.component.util.BaseMapper;
import com.ingenio.transportmanagementservice.domain.entity.ShippingType;
import com.ingenio.transportmanagementservice.domain.repository.ShippingTypeRepository;
import com.ingenio.transportmanagementservice.dto.ShippingTypeDto;
import com.ingenio.transportmanagementservice.service.IShippingTypeService;

@Service
public class ShippingTypeService extends BaseMapper<ShippingType, ShippingTypeDto> implements IShippingTypeService {

    private final ShippingTypeRepository repository;

    public ShippingTypeService(ShippingTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ShippingTypeDto> findAll() {
        return this.entityListToDtoList(repository.findAll());
    }

    @Override
    protected Class<ShippingType> getEntityClass() {
        return ShippingType.class;
    }

    @Override
    protected Class<ShippingTypeDto> getDtoClass() {
        return ShippingTypeDto.class;
    }

}
