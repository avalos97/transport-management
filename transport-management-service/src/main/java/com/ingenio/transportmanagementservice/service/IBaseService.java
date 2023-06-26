package com.ingenio.transportmanagementservice.service;

import java.util.List;

public interface IBaseService<T, ID> {

    T save(T t);    
    
    T update(ID id, T t);

    void delete(ID id);

    T get(ID id);
    
    List<T> getAll();
}