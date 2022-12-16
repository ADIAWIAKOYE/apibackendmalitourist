package com.example.demosecucume.service;

import com.example.demosecucume.Entities.AppRole;
import com.example.demosecucume.Entities.AppUser;

import java.util.List;

public interface AccountService {

    AppUser addUser(AppUser appUser);

    AppRole addRole(AppRole appRole);

    void addRoleToUser(String nom, String nomrole);

    AppUser trouverUserParSonNom(String nom);

    List<AppUser> afficherUser();

    AppUser update(AppUser appUser, Long id);

    String delete(Long id);
}
