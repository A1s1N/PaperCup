package org.service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

//import javax.xml.crypto.Data;
import java.util.Date;
import java.util.UUID;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @UuidGenerator
    private UUID uuid = UUID.randomUUID();

    private String name;

    private Date createdData;
    private Date updatedData;

    // пока все
    // private String email;
    // private String phone;

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

    public Date getCreatedData() {
        return createdData;
    }

    public void setCreatedData(Date createdData) {
        this.createdData = createdData;
    }

    public Date getUpdateData() {
        return updatedData;
    }

    public void setUpdatedData(Date updatedData) {
        this.updatedData = updatedData;
    }

    public Client() {}
    public Client(String name, Date createdData, Date updatedData) {
        this.name = name;
        this.createdData = createdData;
        this.updatedData = updatedData;
    }
}
