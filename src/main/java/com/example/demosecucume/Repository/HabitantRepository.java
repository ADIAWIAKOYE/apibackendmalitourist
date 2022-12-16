package com.example.demosecucume.Repository;

import com.example.demosecucume.Entities.Habitant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitantRepository extends JpaRepository<Habitant, Long> {

    Habitant findByIdhabitant(Long idhabitant);
}
