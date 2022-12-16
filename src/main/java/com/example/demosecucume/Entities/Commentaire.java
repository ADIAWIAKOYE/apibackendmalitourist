package com.example.demosecucume.Entities;

import lombok.*;

import javax.persistence.*;

@Entity
//definis le nom de la table
@Table(name = "commentaire")
//permet d'inclure les getter et setter
@Getter
@Setter
@AllArgsConstructor
//paramettre sans arguments
@NoArgsConstructor
@ToString
public class Commentaire {

    @Id//permet de prendre id_user comme id de cette table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //id de la table User
    private Long idcommentaire;
    private String contenu;

    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    private Region region;
}
