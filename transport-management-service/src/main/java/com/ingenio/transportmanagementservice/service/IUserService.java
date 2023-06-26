package com.ingenio.transportmanagementservice.service;

import com.ingenio.transportmanagementservice.dto.UserDto;

public interface IUserService extends IBaseService<UserDto, Long> {
    UserDto findByUsername(String username);
}
