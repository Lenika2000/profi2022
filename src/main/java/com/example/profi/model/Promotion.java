package com.example.profi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Promotion implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    @OneToMany
    private Set<Prize> prizes;
    @OneToMany
    private Set<Participant> participants;

    public Promotion(Long id, String name, String description, Set<Prize> prizes, Set<Participant> participants) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.prizes = prizes;
        this.participants = participants;
    }

    public Promotion(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Promotion() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Prize> getPrizes() {
        return prizes;
    }

    public void setPrizes(Set<Prize> prizes) {
        this.prizes = prizes;
    }

    public Set<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }
}
