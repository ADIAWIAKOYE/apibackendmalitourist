package com.example.demosecucume.service;

import com.example.demosecucume.Entities.*;
import com.example.demosecucume.Repository.AppUserRepo;
import com.example.demosecucume.Repository.CommentaireRepository;
import com.example.demosecucume.Repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentaireServiceImpl implements CommentaireService{

    private final RegionRepository regionRepository;

    private final CommentaireRepository commentaireRepository;
    private final AppUserRepo appUserRepo;

    @Override
    public Object creercommentaire(Commentaire commentaire, String nomregion, Long iduser) {
        if (regionRepository.findByNomregion(nomregion) == null){
            return massageError.ErreurResponse("cet region n'exite pas ", HttpStatus.BAD_REQUEST, null);
        } else {
             Region region = regionRepository.findByNomregion(nomregion);
            commentaire.setRegion(region);
        }
        if (appUserRepo.findByIduser(iduser) == null){
            return massageError.ErreurResponse("cet user n'exite pas ", HttpStatus.BAD_REQUEST, null);
        }else {
            AppUser appUser = appUserRepo.findByIduser(iduser);
            commentaire.setAppUser(appUser);
        }
        commentaireRepository.save(commentaire);
        return massageError.ErreurResponse("commentaire enregistre avec succes ", HttpStatus.OK, null);
    }

    @Override
    public List<Commentaire> lirecommentaire() {

        return commentaireRepository.findAll();
    }

    @Override
    public Object modifiercommentaire(Long idcommentaire, Commentaire commentaire) {
        if (commentaireRepository.findByIdcommentaire(idcommentaire) != null){
            Commentaire commentaire1 = commentaireRepository.findById(idcommentaire).get();
            commentaire1.setContenu(commentaire.getContenu());
            commentaireRepository.saveAndFlush(commentaire1);
            return massageError.ErreurResponse("commentaire modifier avec succes ", HttpStatus.OK, null);
        }
        return massageError.ErreurResponse("cet commentaire n'existe pas ", HttpStatus.BAD_REQUEST, null);
    }

    @Override
    public Object supprimercommentaire(Long idcommentaire) {
             if (commentaireRepository.findByIdcommentaire(idcommentaire) != null){
                 commentaireRepository.deleteById(idcommentaire);
                 return massageError.ErreurResponse("commentaire supprimer avec succes ", HttpStatus.OK, null);
             }
        return massageError.ErreurResponse("cet commentaire n'existe pas ", HttpStatus.BAD_REQUEST, null);
    }
}
