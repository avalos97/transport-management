package com.ingenio.transportmanagementservice.controller.impl;

import java.util.List;

import static org.springframework.http.ResponseEntity.accepted;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ingenio.transportmanagementservice.controller.IShipmentsController;
import com.ingenio.transportmanagementservice.dto.ShipmentDto;
import com.ingenio.transportmanagementservice.dto.ShippingTypeDto;
import com.ingenio.transportmanagementservice.service.IShipmentService;
import com.ingenio.transportmanagementservice.service.IShippingTypeService;

@RestController
public class ShipmentController implements IShipmentsController {

    private final IShipmentService service;
    private final IShippingTypeService typeSservice;


    public ShipmentController(IShipmentService service, IShippingTypeService typeSservice) {
        this.service = service;
        this.typeSservice = typeSservice;
    }

    @Override
    public ResponseEntity<ShipmentDto> createShipment(@Valid ShipmentDto shipmentDto) {
        return status(HttpStatus.CREATED).body(service.save(shipmentDto));
    }

    @Override
    public ResponseEntity<Void> deleteShipment(Long id) {
        service.delete(id);
        return accepted().build();
    }

    @Override
    public ResponseEntity<List<ShipmentDto>> findAll() {
        return ok(service.getAll());
    }

    @Override
    public ResponseEntity<ShipmentDto> getShipmentById(Long id) {
        return ok(service.get(id));
    }

    @Override
    public ResponseEntity<List<ShipmentDto>> searchShipments(String key, String operation, String value) {
        // TODO Auto-generated method stub
        return IShipmentsController.super.searchShipments(key, operation, value);
    }

    @Override
    public ResponseEntity<ShipmentDto> updateShipment(Long id, @Valid ShipmentDto shipmentDto) {
        return ok(service.update(id, shipmentDto));
    }

    @Override
    public ResponseEntity<List<ShippingTypeDto>> findAllTypes() {
        return ok(typeSservice.findAll());
    }

}
