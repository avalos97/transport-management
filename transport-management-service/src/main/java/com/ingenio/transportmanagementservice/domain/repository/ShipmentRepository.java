package com.ingenio.transportmanagementservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ingenio.transportmanagementservice.domain.entity.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long>{
    
}
