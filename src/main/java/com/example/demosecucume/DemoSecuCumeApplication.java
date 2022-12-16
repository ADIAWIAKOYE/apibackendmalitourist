package com.example.demosecucume;

import com.example.demosecucume.Config.RsakeysConfig;
import com.example.demosecucume.Entities.AppRole;
import com.example.demosecucume.Entities.AppUser;
import com.example.demosecucume.Repository.AppUserRepo;
import com.example.demosecucume.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableConfigurationProperties(RsakeysConfig.class)
public class DemoSecuCumeApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoSecuCumeApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner start(AccountService accountService, AppUserRepo appUserRepo){
        if (appUserRepo.findAll().size()==0){
        return args -> {
            accountService.addRole(new AppRole(null,"USER"));
            accountService.addRole(new AppRole(null,"ADMIN"));

            accountService.addUser(new AppUser(null,"user1","1234",new ArrayList<>()));
            accountService.addUser(new AppUser(null,"admin","1234",new ArrayList<>()));
            accountService.addUser(new AppUser(null,"user2","1234",new ArrayList<>()));
            accountService.addUser(new AppUser(null,"user3","1234",new ArrayList<>()));
            accountService.addUser(new AppUser(null,"admin1","1234",new ArrayList<>()));


            accountService.addRoleToUser("user1","USER");
            accountService.addRoleToUser("user2","USER");
            accountService.addRoleToUser("user3","USER");
            accountService.addRoleToUser("admin","ADMIN");
            accountService.addRoleToUser("admin1","ADMIN");
            accountService.addRoleToUser("user3","ADMIN");
        };
   }else {
           return null;
       }
}
}