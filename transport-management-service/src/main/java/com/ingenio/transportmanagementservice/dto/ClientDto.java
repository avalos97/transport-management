package com.ingenio.transportmanagementservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto extends BaseEntityDto {

    @NotEmpty(message = "Ingrese el nombre")
    private String name;
    private String country;
    private String address;
    private String phone;
    @NotEmpty(message = "The email not be null")
    @Email(message = "Please provide a valid email address")
    private String email;
}
