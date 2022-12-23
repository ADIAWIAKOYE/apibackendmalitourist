package com.example.demosecucume.Repository;

import com.example.demosecucume.Entities.Habitant;
import com.example.demosecucume.Entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitantRepository extends JpaRepository<Habitant, Long> {

    Habitant findByIdhabitant(Long idhabitant);

   List <Habitant> findByRegion(Region idregion);
}
