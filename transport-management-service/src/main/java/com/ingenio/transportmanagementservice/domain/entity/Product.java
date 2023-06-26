package com.ingenio.transportmanagementservice.domain.entity;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "products", catalog = "transport_management_db", schema = "public")
public class Product extends BaseEntity {

    @Column(name = "product_type")
    private String productType;
    private String description;
    private Boolean isActive;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "product")
    private List<Shipment> shipmentList = Collections.emptyList();

}
