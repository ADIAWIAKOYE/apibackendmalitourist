package com.example.demosecucume.Repository;

import com.example.demosecucume.Entities.Commentaire;
import com.example.demosecucume.Entities.Habitant;
import com.example.demosecucume.Entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaireRepository  extends JpaRepository<Commentaire, Long> {

    Commentaire findByIdcommentaire(Long idcommentaire);

    Commentaire findByContenu(String contenu);

    List<Commentaire> findByRegion(Region idregion);
}
