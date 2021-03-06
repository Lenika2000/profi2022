package com.example.profi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Prize implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String description;

    public Prize(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Prize() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
