package com.ingenio.transportmanagementservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingenio.transportmanagementservice.domain.entity.Roles;

@Repository
public interface RolRepository extends JpaRepository<Roles, Long>{
    
}
