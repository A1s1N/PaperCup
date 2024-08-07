package org.service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @UuidGenerator
    private UUID uuid = UUID.randomUUID();

    private String name;

    private Long detailId;

    private Date expiration;

    private Long clientId;

    private int orderNumber;

    private int countProducts;

    private double weight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getCountProducts() {
        return countProducts;
    }

    public void setCountProducts(int countProducts) {
        this.countProducts = countProducts;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Product() {}
    public Product(String name,
                   Long detailId,
                   Date expiration,
                   Long clientId,
                   int orderNumber,
                   int countProducts,
                   double weight) {
        this.name = name;
        this.detailId = detailId;
        this.expiration = expiration;
        this.clientId = clientId;
        this.orderNumber = orderNumber;
        this.countProducts = countProducts;
        this.weight = weight;
    }
}
