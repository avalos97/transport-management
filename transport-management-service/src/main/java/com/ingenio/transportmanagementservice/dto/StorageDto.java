package com.ingenio.transportmanagementservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageDto extends BaseEntityDto {

    private String name;
    private String location;
    private Long storageTypeId;
    private String storageTypeName;
}
