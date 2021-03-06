package com.example.profi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Participant implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Participant(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Participant() {
    }

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
}
