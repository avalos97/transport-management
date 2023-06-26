package com.ingenio.transportmanagementservice.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "storage_type", catalog = "transport_management_db", schema = "public")
public class StorageType extends BaseEntity {
    private String name;
 
}
