package com.ingenio.transportmanagementservice.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ingenio.transportmanagementservice.component.exception.customExceptions.InvalidDataException;
import com.ingenio.transportmanagementservice.component.exception.customExceptions.RecordNotFoundException;
import com.ingenio.transportmanagementservice.component.exception.error.ErrorCode;
import com.ingenio.transportmanagementservice.component.util.BaseMapper;
import com.ingenio.transportmanagementservice.domain.entity.Roles;
import com.ingenio.transportmanagementservice.domain.entity.User;
import com.ingenio.transportmanagementservice.domain.repository.RolRepository;
import com.ingenio.transportmanagementservice.domain.repository.UserRepository;
import com.ingenio.transportmanagementservice.dto.UserDto;
import com.ingenio.transportmanagementservice.service.IUserService;

@Service
public class UserService extends BaseMapper<User, UserDto> implements IUserService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final RolRepository rolesRepository;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder, RolRepository rolesRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public UserDto save(UserDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        User entity = repository.save(this.dtoToEntity(dto));
        return this.entityToDto(entity);
    }

    @Override
    public UserDto update(Long id, UserDto dto) {
        UserDto findUser = this.get(id); // * validamos que exista el cliente */
        if (StringUtils.isEmpty(dto.getPassword()) || dto.getPassword() != null) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        } else {
            dto.setPassword(findUser.getPassword());
        }
        User entity = this.dtoToEntity(dto);
        return this.entityToDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        this.get(id); // * validamos que exista el cliente */
        repository.deleteById(id);
    }

    @Override
    public UserDto get(Long id) {
        User entity = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        return this.entityToDto(entity);
    }

    @Override
    public List<UserDto> getAll() {
        return this.entityListToDtoList(repository.findAll());

    }

    @Override
    public UserDto findByUsername(String username) {

        if (StringUtils.isBlank(username.trim()))
            throw new InvalidDataException("username invalid");

        return this.entityToDto(repository.findByUsername(username)
                .orElseThrow(() -> new RecordNotFoundException(ErrorCode.RECORD_NOT_FOUND)));
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    protected Class<UserDto> getDtoClass() {
        return UserDto.class;
    }

    @Override
    public User dtoToEntity(UserDto dto) {
        User entity = super.dtoToEntity(dto);
        entity.getRoles().clear();
        Roles role = rolesRepository.findById(dto.getRolesId()).orElse(null); // Busca el role por id
        if (role != null) {
            entity.getRoles().add(role); // Agrega el role existente
        }
        return entity;
    }

}
