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

    private String name;

    private double operationTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(double operationTime) {
        this.operationTime = operationTime;
    }

    public Operation() {}

    public Operation(String name, double operationTime) {
        this.name = name;
        this.operationTime = operationTime;
    }
}
