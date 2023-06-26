package com.ingenio.transportmanagementservice.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ingenio.transportmanagementservice.domain.entity.Shipment;
import com.ingenio.transportmanagementservice.domain.entity.Storage;
import com.ingenio.transportmanagementservice.dto.ShipmentDto;
import com.ingenio.transportmanagementservice.dto.StorageDto;

@Configuration
public class ModelMapperConfig {

    /**
     * @return elemento de configuracion de model mapper
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        return customMapping(mapper);
    }

    private ModelMapper customMapping(ModelMapper modelMapper) {
        TypeMap<Storage, StorageDto> propertyMapper = modelMapper.createTypeMap(Storage.class,
                StorageDto.class);
        propertyMapper.addMappings(
                mapper -> {
                    mapper.map(src -> src.getStorageType().getName(),
                            StorageDto::setStorageTypeName);
                });

        TypeMap<Shipment, ShipmentDto> propertyShipmentMapper = modelMapper.createTypeMap(Shipment.class,
                ShipmentDto.class);
        propertyShipmentMapper.addMappings(mapper -> {
            mapper.map(src -> src.getProduct().getProductType(), ShipmentDto::setProductName);
            mapper.map(src -> src.getClient().getName(), ShipmentDto::setClientName);
            mapper.map(src -> src.getStorage().getName(), ShipmentDto::setStorageName);
            mapper.map(src -> src.getShippingType().getName(), ShipmentDto::setShippingTypeName);
        });

        return modelMapper;
    }
}