package com.example.demosecucume.service;

import com.example.demosecucume.Entities.Commentaire;
import com.example.demosecucume.Entities.Habitant;
import com.example.demosecucume.Entities.Pays;
import com.example.demosecucume.Entities.Region;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentaireService {

    Object creercommentaire(Commentaire commentaire, String nomregion, String nom );

    List<Commentaire> lirecommentaire();

    Object modifiercommentaire(Long idcommentaire, Commentaire commentaire);

    Object supprimercommentaire(Long idcommentaire);

    List<Commentaire> affichecommentaire(Region idregion);
}
