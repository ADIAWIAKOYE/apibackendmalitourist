package com.example.demosecucume.service;

import com.example.demosecucume.Entities.AppUser;
import com.example.demosecucume.Repository.AppUserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private AppUserRepo appUserRepo;

    public CustomUserDetailsService(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.trim().isEmpty()) {
            throw new UsernameNotFoundException("username vide");
        }
        //recupere le collaborateurs par son username
        AppUser appUser= appUserRepo.findByNom(username);
        if(appUser == null){
            //si le coll n'existe pas retouner cette erreur
            log.error("Utilisateur non trouvé");

            throw new UsernameNotFoundException("Utilisateur non trouvé");
        } else{
            //sinon sil existe retouner ce messsage
            log.info("Utilisateur  trouvé",username);

        }


        //noonnnnnnnnnnnn compris
        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
        appUser.getAppRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getNomrole())));
        return new  org.springframework.security.core.userdetails.User(appUser.getNom(),appUser.getPassword(),authorities);
        ////////////////////////
        // L'interface UserDetails  représente un objet
        // utilisateur authentifié et Spring Security fournit une implémentation prête à l'emploi de org.......
    }
}
