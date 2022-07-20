package com.korolyovegor.petList.model;

import com.korolyovegor.petList.model.security.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "pets")
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

//    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "species")
    private Species species;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 10)
    private PetGenderType gender;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner")
    private User owner;

    public Pet() {
        species = new Species();
    }

    public Pet(Species species, LocalDate birthdate, PetGenderType gender, String name, User owner) {
        this.species = species;
        this.birthdate = birthdate;
        this.gender = gender;
        this.name = name;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", species=" + species +
                ", birthdate=" + birthdate +
                ", gender=" + gender +
                ", name='" + name + '\'' +
                ", species='" + species.getName() + '\'' +
                ", owner='" + owner.getUsername() + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetGenderType getGender() {
        return gender;
    }

    public void setGender(PetGenderType gender) {
        this.gender = gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}