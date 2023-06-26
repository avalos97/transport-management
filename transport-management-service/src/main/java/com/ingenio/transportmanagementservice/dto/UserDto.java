package com.ingenio.transportmanagementservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends BaseEntityDto {

    private String name;
    private String username;
    private String password;

    // * Referencias a otras entidades se presentan por sus id's
    private Long rolesId;
}
