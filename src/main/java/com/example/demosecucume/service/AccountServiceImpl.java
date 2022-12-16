package com.example.demosecucume.service;

import com.example.demosecucume.Entities.AppRole;
import com.example.demosecucume.Entities.AppUser;
import com.example.demosecucume.Repository.AppRoleRepo;
import com.example.demosecucume.Repository.AppUserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService {

private PasswordEncoder passwordEncoder;
    private AppUserRepo appUserRepo;
    private AppRoleRepo appRoleRepo;

    public AccountServiceImpl(PasswordEncoder passwordEncoder, AppUserRepo appUserRepo, AppRoleRepo appRoleRepo) {
        this.passwordEncoder = passwordEncoder;
        this.appUserRepo = appUserRepo;
        this.appRoleRepo = appRoleRepo;
    }

    @Override
    public AppUser addUser(AppUser appUser) {
    log.info("Saving new user to database",appUser.getNom());
    appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepo.save(appUser);
    }

    @Override
    public AppRole addRole(AppRole appRole) {

        return appRoleRepo.save(appRole);
    }

    @Override
    public void addRoleToUser(String nom, String nomrole) {
      AppUser appUser=appUserRepo.findByNom(nom);
      AppRole appRole=appRoleRepo.findByNomrole(nomrole);
      appUser.getAppRoles().add(appRole);
    }

    @Override
    public AppUser trouverUserParSonNom(String nom) {

        return appUserRepo.findByNom(nom);
    }

    @Override
    public List<AppUser> afficherUser() {

        return appUserRepo.findAll();
    }

    @Override
    public AppUser update(AppUser appUser, Long id) {

        return appUserRepo.findById(id)
                .map(appUser1 -> {
                    appUser1.setNom(appUser.getNom());
                    appUser1.setPassword(appUser.getPassword());
                    return appUserRepo.save(appUser1);
                }).orElseThrow(() -> new RuntimeException("User non trouve !"));
    }

    @Override
    public String delete(Long id) {

        appUserRepo.deleteById(id);

        return "User supprimer avec succes";
    }
}
