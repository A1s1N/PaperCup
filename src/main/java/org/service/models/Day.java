package org.service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Date;
import java.util.UUID;
import javax.xml.crypto.Data;


@Entity
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //подтягивать номер заказа из 1С
    @UuidGenerator
    private UUID uuid = UUID.randomUUID();

    private Date currentDate;

    private int requiredNumber;

    private int madeNumber;

    private Long orderId;
    //??
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public int getRequiredNumber() {
        return requiredNumber;
    }

    public void setRequiredNumber(int requiredNumber) {
        this.requiredNumber = requiredNumber;
    }

    public int getMadeNumber() {
        return madeNumber;
    }

    public void setMadeNumber(int madeNumber) {
        this.madeNumber = madeNumber;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Day() {}
    public Day(Date currentDate, int requiredNumber, int madeNumber, Long orderId) {
        this.currentDate = currentDate;
        this.requiredNumber = requiredNumber;
        this.madeNumber = madeNumber;
        this.orderId = orderId;
    }
}
