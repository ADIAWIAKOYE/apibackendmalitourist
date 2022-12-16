package com.example.demosecucume.Entities;

import lombok.*;

import javax.persistence.*;

//transforme cette classe en table
@Entity
//definis le nom de la table
@Table(name = "approle")
//permet d'inclure les getter et setter
@Getter
@Setter
@AllArgsConstructor
//paramettre sans arguments
@ToString
@NoArgsConstructor
public class AppRole {
    @Id//permet de prendre id_user comme id de cette table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //id de la table User
    private Long idrole;
    private String nomrole;
}
