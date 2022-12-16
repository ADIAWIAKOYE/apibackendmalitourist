package com.example.demosecucume.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

//transforme cette classe en table
@Entity
//definis le nom de la table
@Table(name = "appuser")
//permet d'inclure les getter et setter
@Getter
@Setter
@AllArgsConstructor
//paramettre sans arguments
@NoArgsConstructor
@ToString
public class AppUser {

    @Id//permet de prendre id_user comme id de cette table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //id de la table User
    private Long iduser;
    private String nom;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> appRoles=new ArrayList<>();
}
