package com.ingenio.transportmanagementservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ingenio.transportmanagementservice.component.exception.customExceptions.RecordNotFoundException;
import com.ingenio.transportmanagementservice.component.util.BaseMapper;
import com.ingenio.transportmanagementservice.domain.entity.Product;
import com.ingenio.transportmanagementservice.domain.repository.ProductRepository;
import com.ingenio.transportmanagementservice.dto.ProductDto;
import com.ingenio.transportmanagementservice.service.IProductService;

@Service
public class ProductService extends BaseMapper<Product, ProductDto> implements IProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductDto save(ProductDto dto) {
        Product entity = repository.save(this.dtoToEntity(dto));
        return this.entityToDto(entity);
    }

    @Override
    public ProductDto update(Long id, ProductDto dto) {
        this.get(id); // * validamos que exista el producto */
        Product entity = this.dtoToEntity(dto);
        return this.entityToDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        this.get(id); // * validamos que exista el producto */
        repository.deleteById(id);
    }

    @Override
    public ProductDto get(Long id) {
        Product entity = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
        return this.entityToDto(entity);
    }

    @Override
    public List<ProductDto> getAll() {
        return this.entityListToDtoList(repository.findAll());
    }

    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    protected Class<ProductDto> getDtoClass() {
        return ProductDto.class;
    }

}
