package com.ingenio.transportmanagementservice.domain.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "shipments", catalog = "transport_management_db", schema = "public")
public class Shipment extends BaseEntity {

    private Integer quantity;

    @Column(name = "registration_date")
    private Timestamp registrationDate;

    @Column(name = "delivery_date")
    private Timestamp deliveryDate;

    @Column(name = "shipping_price")
    private BigDecimal shippingPrice;

    private BigDecimal discount;

    @Column(name = "fleet_vehicle_number")
    private String fleetVehicleNumber;

    @Column(name = "guide_number")
    private String guideNumber;

    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "storage_id", referencedColumnName = "id", nullable = false)
    private Storage storage;

    @ManyToOne
    @JoinColumn(name = "shipping_type_id", referencedColumnName = "id", nullable = false)
    private ShippingType shippingType;

}
