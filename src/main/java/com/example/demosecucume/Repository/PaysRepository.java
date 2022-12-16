package com.example.demosecucume.Repository;

import com.example.demosecucume.Entities.Pays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaysRepository extends JpaRepository<Pays, Long> {
    Pays findByNompays(String nompays);

    Pays findByIdpays(Long idpays);
}
