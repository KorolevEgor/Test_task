package com.korolyovegor.petList.repository;

import com.korolyovegor.petList.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface SpeciesRepository extends JpaRepository<Species, Long> {
}
