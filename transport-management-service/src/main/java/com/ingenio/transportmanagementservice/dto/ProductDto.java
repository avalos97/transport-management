package com.ingenio.transportmanagementservice.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto extends BaseEntityDto {

    @NotEmpty(message = "The product type not be null")
    private String productType;
    private String description;
}
