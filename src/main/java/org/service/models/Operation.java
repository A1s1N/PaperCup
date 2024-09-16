package org.service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @UuidGenerator
    private UUID uuid = UUID.randomUUID();

    private Long productId;

    private String name;

    private String operationTime;

    private String orderliness;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderliness() {
        return orderliness;
    }

    public void setOrderliness(String orderliness) {
        this.orderliness = orderliness;
    }

    public Operation() {}

    public Operation(String name, String operationTime, Long productId, String orderliness) {
        this.name = name;
        this.operationTime = operationTime;
        this.productId = productId;
        this.orderliness = orderliness;
    }
}
