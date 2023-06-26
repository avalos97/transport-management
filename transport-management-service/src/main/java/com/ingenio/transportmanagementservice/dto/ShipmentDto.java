package com.ingenio.transportmanagementservice.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipmentDto extends BaseEntityDto {

    private Integer quantity;
    private Timestamp registrationDate;
    private Timestamp deliveryDate;
    private BigDecimal shippingPrice;
    private BigDecimal discount;
    private String fleetVehicleNumber;
    private String guideNumber;

    @NotNull(message = "the product cannot be null")
    private Long productId;    
    private String productName;
    @NotNull(message = "the client cannot be null")
    private Long clientId;
    private String clientName;
    @NotNull(message = "the warehouse or seaport cannot be null")
    private Long storageId;
    private String storageName;
    @NotNull(message = "shipping type cannot be null")
    private Long shippingTypeId;
    private String shippingTypeName;
}
