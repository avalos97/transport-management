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
@Table(name = "clients", catalog = "transport_management_db", schema = "public")
public class Client extends BaseEntity {

    private String name;
    private String country;
    private String address;
    private String email;
    private String phone;
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "client")
    private List<Shipment> shipmentList = Collections.emptyList();
}
