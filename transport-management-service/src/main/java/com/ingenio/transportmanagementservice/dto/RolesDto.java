package com.ingenio.transportmanagementservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolesDto extends BaseEntityDto {
    private String name;
    private String description;
}
