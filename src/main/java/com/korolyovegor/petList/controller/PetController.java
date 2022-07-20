package com.korolyovegor.petList.controller;

import com.korolyovegor.petList.model.Pet;
import com.korolyovegor.petList.model.security.User;
import com.korolyovegor.petList.repository.PetRepository;
import com.korolyovegor.petList.repository.SpeciesRepository;
import com.korolyovegor.petList.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {

    private final PetRepository petRepository;
    private final SpeciesRepository speciesRepository;
    private final UserRepository userRepository;

    @Autowired
    public PetController(PetRepository petRepository,
                         SpeciesRepository speciesRepository,
                         UserRepository userRepository) {
        this.petRepository = petRepository;
        this.speciesRepository = speciesRepository;
        this.userRepository = userRepository;
    }

    private boolean checkRolesOnPet(Long petId) {
        User user = userRepository.getAuthenticatedUser();
        return user.getPets().stream()
                .map(Pet::getId)
                .anyMatch((id) -> id.equals(petId));
    }

    @GetMapping("/pets")
    public List<Pet> getAllPets() {
        List<Pet> pets = petRepository.findAllForOwner(userRepository.getAuthenticatedUser().getId());
        System.out.println(pets);
        return pets;
    }

    // получение любого животного по id (без проверки прав доступа)
    @GetMapping("pets/{id}")
    public Pet getPetById(@PathVariable Long id) {
//        if (checkRolesOnPet(id)) {
            Pet pet = petRepository.getPetById(id).orElse(new Pet());
            System.out.println(pet);
            return pet;
//        } else {
//            System.out.println("Нет прав для просмотра информации");
//            return new Pet();
//        }
    }

    @PostMapping("/pets")
    public Pet savePet(@RequestBody Pet pet) {
        if (checkRolesOnPet(pet.getId())) {
            petRepository.save(pet);
            System.out.println(pet);
            return pet;
        } else {
            return new Pet();
        }
    }

    @PutMapping("/pets")
    public Pet updatePet(@RequestBody Pet pet) {
        if (checkRolesOnPet(pet.getId())) {
            petRepository.save(pet);
            System.out.println(pet);
            return pet;
        } else {
            return new Pet();
        }
    }

    @DeleteMapping("/pets/{id}")
    public void deletePet(@PathVariable Long id) {
        if (checkRolesOnPet(id)) {
            petRepository.deleteById(id);
        }
    }
}
