package org.service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import javax.xml.crypto.Data;
import java.util.UUID;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @UuidGenerator
    private UUID uuid = UUID.randomUUID();

    private String name;

    private Data createdData;
    private Data updatedData;

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

    public Data getCreatedData() {
        return createdData;
    }

    public void setCreatedData(Data createdData) {
        this.createdData = createdData;
    }

    public Data getUpdateData() {
        return updatedData;
    }

    public void setUpdatedData(Data updatedData) {
        this.updatedData = updatedData;
    }

    public Client() {}
    public Client(String name, Data createdData, Data updatedData) {
        this.name = name;
        this.createdData = createdData;
        this.updatedData = updatedData;
    }
}
