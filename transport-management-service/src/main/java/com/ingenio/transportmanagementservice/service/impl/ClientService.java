package com.ingenio.transportmanagementservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ingenio.transportmanagementservice.component.exception.customExceptions.RecordNotFoundException;
import com.ingenio.transportmanagementservice.component.util.BaseMapper;
import com.ingenio.transportmanagementservice.domain.entity.Client;
import com.ingenio.transportmanagementservice.domain.repository.ClientRepository;
import com.ingenio.transportmanagementservice.dto.ClientDto;
import com.ingenio.transportmanagementservice.service.IClientService;

@Service
public class ClientService extends BaseMapper<Client, ClientDto> implements IClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClientDto save(ClientDto client) {
        Client entity = repository.save(this.dtoToEntity(client));
        return this.entityToDto(entity);
    }

    @Override
    public ClientDto update(Long id, ClientDto dto) {
        this.get(id); // * validamos que exista el cliente */
        Client entity = this.dtoToEntity(dto);
        return this.entityToDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        this.get(id); // * validamos que exista el cliente */
        repository.deleteById(id);
    }

    @Override
    public ClientDto get(Long id) {
        Client entity = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        return this.entityToDto(entity);
    }

    @Override
    public List<ClientDto> getAll() {
        return this.entityListToDtoList(repository.findAll());
    }

    // * Mapeo */

    @Override
    protected Class<ClientDto> getDtoClass() {
        return ClientDto.class;
    }

    @Override
    protected Class<Client> getEntityClass() {
        return Client.class;
    }

}
