package org.service.models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "'ORDER'")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //подтягивать номер заказа из 1С
    @UuidGenerator
    private UUID uuid = UUID.randomUUID();

    private String name;

    private String deadline;

    private String createdDate; // может всем классам?

    private String updatedDate;

    private Long productId;
    // итоговое количество на какую-то дату (в календарь), который нужно сделать
    private Long clientId;

    private String totalDate; // +-

    private Long countProducts;

    private int orderNumber; // сделать все такие поля только положительными +-

    private double totalWeight;

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

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getTotalDate() {
        return totalDate;
    }

    public void setTotalDate(String totalDate) {
        this.totalDate = totalDate;
    }

    public Long getCountProducts() {
        return countProducts;
    }

    public void setCountProducts(Long countProducts) {
        this.countProducts = countProducts;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Order() {}
    public Order(String name,
                 String deadline,
                 String createdDate,
                 String updatedDate,
                 Long productId,
                 Long clientId,
                 String totalDate,
                 Long countProducts,
                 double totalWeight) {
        this.name = name;
        this.deadline = deadline;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.productId = productId;
        this.clientId = clientId;
        this.totalDate = totalDate;
        this.countProducts = countProducts;
        this.totalWeight = totalWeight;
    }
}