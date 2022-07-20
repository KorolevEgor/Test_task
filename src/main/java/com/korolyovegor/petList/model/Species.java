package com.korolyovegor.petList.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "species")
public class Species implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, length = 70)
    private SpeciesType name;

    public Species() {
    }

    public Species(SpeciesType name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Species{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @JsonIgnore
    @OneToMany(mappedBy = "species")
    private Set<Pet> pets = new LinkedHashSet<>();

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public SpeciesType getName() {
        return name;
    }

    public void setName(SpeciesType name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}