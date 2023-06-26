package com.ingenio.transportmanagementservice.service.impl;

import static com.ingenio.transportmanagementservice.component.util.Constants.PERCENTAGE_0_03;
import static com.ingenio.transportmanagementservice.component.util.Constants.PERCENTAGE_0_05;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

import org.springframework.stereotype.Service;

import com.ingenio.transportmanagementservice.component.exception.customExceptions.RecordNotFoundException;
import com.ingenio.transportmanagementservice.component.util.BaseMapper;
import com.ingenio.transportmanagementservice.domain.entity.Shipment;
import com.ingenio.transportmanagementservice.domain.repository.SearchCriteria;
import com.ingenio.transportmanagementservice.domain.repository.ShipmentRepository;
import com.ingenio.transportmanagementservice.dto.ShipmentDto;
import com.ingenio.transportmanagementservice.service.IShipmentService;

@Service
public class ShipmentService extends BaseMapper<Shipment, ShipmentDto> implements IShipmentService {

    private static final Map<Long, BigDecimal> DISCOUNTS = Map.of(
            1L, PERCENTAGE_0_05,
            2L, PERCENTAGE_0_03);

    private final ShipmentRepository repository;

    public ShipmentService(ShipmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public ShipmentDto save(ShipmentDto dto) {
        dto.setDiscount(calculateDiscount(dto.getQuantity(), dto.getShippingTypeId(), dto.getShippingPrice()));
        Shipment entity = repository.save(this.dtoToEntity(dto));
        return this.entityToDto(entity);
    }

    @Override
    public ShipmentDto update(Long id, ShipmentDto dto) {
        dto.setDiscount(calculateDiscount(dto.getQuantity(), dto.getShippingTypeId(), dto.getShippingPrice()));
        this.get(id); // * validamos que exista el envio */
        Shipment entity = this.dtoToEntity(dto);
        return this.entityToDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        this.get(id); // * validamos que exista el envio */
        repository.deleteById(id);
    }

    @Override
    public ShipmentDto get(Long id) {
        Shipment entity = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        return this.entityToDto(entity);
    }

    @Override
    public List<ShipmentDto> getAll() {
        return this.entityListToDtoList(repository.findAll());

    }

    @Override
    public List<ShipmentDto> searchShipments(SearchCriteria criteria) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchShipments'");
    }

    @Override
    protected Class<ShipmentDto> getDtoClass() {
        return ShipmentDto.class;
    }

    @Override
    protected Class<Shipment> getEntityClass() {
        return Shipment.class;
    }

    public BigDecimal calculateDiscount(Integer quantity, Long shippingTypeId, BigDecimal shippingPrice) {
        
        BinaryOperator<BigDecimal> calculate = BigDecimal::multiply;
        
        return quantity > 10 && DISCOUNTS.containsKey(shippingTypeId) ?
                calculate.apply(shippingPrice, DISCOUNTS.get(shippingTypeId)) :
                BigDecimal.ZERO;
    }

}
