package org.service.models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;
//import javax.xml.crypto.Data;


@Entity
@Table(name = "'DAY'")
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //подтягивать номер заказа из 1С
    @UuidGenerator
    private UUID uuid = UUID.randomUUID();

    private Date curentDate;

    private Long requiredNumber;

    private Long madeNumber;

    private Long orderId;
    //??
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCurrentDate() {
        return curentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.curentDate = currentDate;
    }

    public Long getRequiredNumber() {
        return requiredNumber;
    }

    public void setRequiredNumber(Long requiredNumber) {
        this.requiredNumber = requiredNumber;
    }

    public long getMadeNumber() {
        return madeNumber;
    }

    public void setMadeNumber(long madeNumber) {
        this.madeNumber = madeNumber;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Day() {}
    public Day(Date curentDate, Long requiredNumber, Long madeNumber, Long orderId) {
        this.curentDate = curentDate;
        this.requiredNumber = requiredNumber;
        this.madeNumber = madeNumber;
        this.orderId = orderId;
    }
}
