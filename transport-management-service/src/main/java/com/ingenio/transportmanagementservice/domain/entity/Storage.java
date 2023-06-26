package com.ingenio.transportmanagementservice.domain.entity;

import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "storage", catalog = "transport_management_db", schema = "public")
public class Storage extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "storage_type_id", referencedColumnName = "id")
    private StorageType storageType;
    private String name;
    private String location;
    private Boolean isActive;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "storage")
    private List<Shipment> shipmentList = Collections.emptyList();

}
