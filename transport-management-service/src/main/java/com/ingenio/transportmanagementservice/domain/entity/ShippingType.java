package com.ingenio.transportmanagementservice.domain.entity;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "shipping_type", catalog = "transport_management_db", schema = "public")
public class ShippingType extends BaseEntity {
    
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shippingType", orphanRemoval = true)
    private List<Shipment> shipmentList = Collections.emptyList();

}
