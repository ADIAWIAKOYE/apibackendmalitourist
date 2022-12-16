package com.example.demosecucume.Repository;

import com.example.demosecucume.Entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepo extends JpaRepository<AppRole, Long> {

    AppRole findByNomrole(String nomrole);

}
