package com.example.demosecucume.Repository;

import com.example.demosecucume.Entities.Commentaire;
import com.example.demosecucume.Entities.Habitant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentaireRepository  extends JpaRepository<Commentaire, Long> {

    Commentaire findByIdcommentaire(Long idcommentaire);

    Commentaire findByContenu(String contenu);
}
