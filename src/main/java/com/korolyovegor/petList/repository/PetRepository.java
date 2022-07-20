package com.korolyovegor.petList.repository;

import com.korolyovegor.petList.model.Pet;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Optional<Pet> getPetById(Long id);
    Optional<Pet> getPetByName(String name);

    void deleteById(Long id);
    void deletePetByName(String name);

    default List<Pet> findAllForOwner(Long ownerId) {
        return findAll().stream()
                .filter(pet -> pet.getOwner().getId().equals(ownerId))
                .collect(Collectors.toList());
    }
}
