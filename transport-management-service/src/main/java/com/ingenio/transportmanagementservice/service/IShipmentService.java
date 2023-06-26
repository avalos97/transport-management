package com.ingenio.transportmanagementservice.service;

import java.util.List;

import com.ingenio.transportmanagementservice.domain.repository.SearchCriteria;
import com.ingenio.transportmanagementservice.dto.ShipmentDto;

public interface IShipmentService extends IBaseService<ShipmentDto, Long> {

    public List<ShipmentDto> searchShipments(SearchCriteria criteria);
    
}
