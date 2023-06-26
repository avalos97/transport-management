package com.ingenio.transportmanagementservice.controller.impl;

import java.util.List;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.accepted;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ingenio.transportmanagementservice.controller.IProductController;
import com.ingenio.transportmanagementservice.dto.ProductDto;
import com.ingenio.transportmanagementservice.service.IProductService;

@RestController
public class ProductController implements IProductController {

    private final IProductService service;

    public ProductController(IProductService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<ProductDto> createProduct(@Valid ProductDto req) {
       return status(HttpStatus.CREATED).body(service.save(req));
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long id) {
        service.delete(id);
        return accepted().build();
    }

    @Override
    public ResponseEntity<List<ProductDto>> findAll() {
        return ok(service.getAll());
    }

    @Override
    public ResponseEntity<ProductDto> getProductById(Long id) {
        return ok(service.get(id));
    }

    @Override
    public ResponseEntity<ProductDto> updateProduct(Long id, @Valid ProductDto req) {
        return ok(service.update(id, req));
    }

}
