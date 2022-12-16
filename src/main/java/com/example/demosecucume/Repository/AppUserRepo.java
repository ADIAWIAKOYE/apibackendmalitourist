package com.example.demosecucume.Repository;

import com.example.demosecucume.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    AppUser findByIduser(Long iduser);
    AppUser findByNom(String nom);

}
