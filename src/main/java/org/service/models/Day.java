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

    //private Date curentDate;
    private String month;

    private String day;
    //??
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

//    public Date getCurrentDate() {
//        return curentDate;
//    }
//
//    public void setCurrentDate(Date currentDate) {
//        this.curentDate = currentDate;
//    }

//    public Long getRequiredNumberOfBlanks() {
//        return requiredNumberOfBlanks;
//    }
//
//    public void setRequiredNumberOfBlanks(Long requiredNumberOfBlanks) {
//        this.requiredNumberOfBlanks = requiredNumberOfBlanks;
//    }
//
//    public long getMadeNumberOfBlanks() {
//        return madeNumberOfBlanks;
//    }
//
//    public void setMadeNumberOfBlanks(long madeNumberOfBlanks) {
//        this.madeNumberOfBlanks = madeNumberOfBlanks;
//    }
//
//    public Long getOperationId() {
//        return operationId;
//    }
//
//    public void setOperationId(Long operationId) {
//        this.operationId = operationId;
//    }
//
//    public Long getTotalNumberOfBlanks() {
//        return totalNumberOfBlanks;
//    }
//
//    public void setTotalNumberOfBlanks(Long totalNumberOfBlanks) {
//        this.totalNumberOfBlanks = totalNumberOfBlanks;
//    }

    public Day() {}
//    public Day(Date curentDate, Long requiredNumberOfBlanks, Long madeNumberOfBlanks, Long operationId) {
//        this.operationId = operationId;
//        this.requiredNumberOfBlanks = requiredNumberOfBlanks;
//        this.madeNumberOfBlanks = madeNumberOfBlanks;
//        this.operationId = operationId;
//    }
    public Day(String month, String day) {
        this.month = month;
        this.day = day;
    }
}
