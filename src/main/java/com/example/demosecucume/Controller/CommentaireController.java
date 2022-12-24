package com.example.demosecucume.Controller;

import com.example.demosecucume.Entities.Commentaire;
import com.example.demosecucume.Entities.Habitant;
import com.example.demosecucume.Entities.Region;
import com.example.demosecucume.service.CommentaireService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@Api(value = "hello", description = "Entité Habitant de API MaliTourist")
@RestController
@RequestMapping("/commentaire")
@AllArgsConstructor
public class CommentaireController {

    @Autowired
    private final CommentaireService commentaireService;

    @ApiOperation(value = "Juste pour ajouter le chiffre de la population pour l'API MaliTourist")
   // @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping("/createcommentaire/{nomregion}/{nom}")
    public Object createcommentaire(@RequestBody Commentaire commentaire, @PathVariable String nomregion, @PathVariable String nom){

        return commentaireService.creercommentaire(commentaire, nomregion, nom);
    }

    @ApiOperation(value = "Juste pour avoir la liste de tout les chiffres de population par date de l'API MaliTourist")
    @PreAuthorize("hasAuthority('SCOPE_USER') or hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/readcommentaire")
    public List<Commentaire> readcommentaire(){

        return commentaireService.lirecommentaire();
    }

    @ApiOperation(value = "Juste pour modifier un chiffre de population de  l'API MaliTourist")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PutMapping("/updatecommentaire/{idcommentaire}")
    public Object updatecommentaire(@PathVariable Long idcommentaire, @RequestBody Commentaire commentaire){
        return commentaireService.modifiercommentaire(idcommentaire, commentaire);
    }

    @ApiOperation(value = "Juste pour supprimer un chiffre de population de l'API MaliTourist")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/deletecommentaire/{idcommentaire}")
    public Object deletecommentaire(@PathVariable Long idcommentaire){
        return commentaireService.supprimercommentaire(idcommentaire);
    }

    @GetMapping("/readCommentairett/{idregion}")
    public List<Commentaire> affichecommentaire(Region idregion) {

        return commentaireService.affichecommentaire(idregion);
    }

    @GetMapping("/readCommentairenombre/{idregion}")
    public int affichecommentairenombre(Region idregion) {

        return commentaireService.affichecommentaire(idregion).size();
    }
}
