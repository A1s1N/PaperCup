package org.service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //подтягивать номер заказа из 1С
    @UuidGenerator
    private UUID uuid = UUID.randomUUID();

    private String name;

    private Date deadline;

    private Date createdDate; // может всем классам?

    private Date updatedDate;

    private Long productId;
// итоговое количество на какую-то дату (в календарь), который нужно сделать
    private Long clientId;

    private Date totalDate; // +-

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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
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

    public Date getTotalDate() {
        return totalDate;
    }

    public void setTotalDate(Date totalDate) {
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
                 Date deadline,
                 Date createdDate,
                 Date updatedDate,
                 Long productId,
                 Long clientId,
                 Date totalDate,
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
