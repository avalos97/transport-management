package com.ingenio.transportmanagementservice.controller.impl;

import static org.springframework.http.ResponseEntity.accepted;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import com.ingenio.transportmanagementservice.controller.IUserController;
import com.ingenio.transportmanagementservice.dto.UserDto;
import com.ingenio.transportmanagementservice.service.IUserService;

@Validated
@RestController
public class UserController implements IUserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<UserDto> createUser(@Valid UserDto req) {
        return status(HttpStatus.CREATED).body(service.save(req));
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        service.delete(id);
        return accepted().build();
    }

    @Override
    public ResponseEntity<List<UserDto>> findAll() {
        return ok(service.getAll());
    }

    @Override
    public ResponseEntity<UserDto> getUserById(Long id) {
        return ok(service.get(id));
    }

    @Override
    public ResponseEntity<UserDto> updateUser(Long id, @Valid UserDto req) {
        return ok(service.update(id, req));
    }
    
}
