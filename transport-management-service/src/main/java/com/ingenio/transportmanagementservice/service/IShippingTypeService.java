package com.ingenio.transportmanagementservice.service;

import java.util.List;

import com.ingenio.transportmanagementservice.dto.ShippingTypeDto;

public interface IShippingTypeService {
    List<ShippingTypeDto> findAll();

}
