package org.service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class DayOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @UuidGenerator
    private UUID uuid = UUID.randomUUID();

    private Long requiredNumberOfBlanks;

    private Long madeNumberOfBlanks;

    private Long operationId;

    private Long countPeople;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequiredNumberOfBlanks() {
        return requiredNumberOfBlanks;
    }

    public void setRequiredNumberOfBlanks(Long requiredNumberOfBlanks) {
        this.requiredNumberOfBlanks = requiredNumberOfBlanks;
    }

    public Long getMadeNumberOfBlanks() {
        return madeNumberOfBlanks;
    }

    public void setMadeNumberOfBlanks(Long madeNumberOfBlanks) {
        this.madeNumberOfBlanks = madeNumberOfBlanks;
    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public Long getCountPeople() {
        return countPeople;
    }

    public void setCountPeople(Long countPeople) {
        this.countPeople = countPeople;
    }

    public DayOperation() {}

    public DayOperation(Long requiredNumberOfBlanks, Long madeNumberOfBlanks, Long operationId, Long countPeople) {
        this.requiredNumberOfBlanks = requiredNumberOfBlanks;
        this.madeNumberOfBlanks = madeNumberOfBlanks;
        this.operationId = operationId;
        this.countPeople = countPeople;
    }
}
