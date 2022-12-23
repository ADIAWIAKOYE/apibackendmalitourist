package com.example.demosecucume.Controller;

import com.example.demosecucume.Entities.Habitant;
import com.example.demosecucume.Entities.Region;
import com.example.demosecucume.Repository.RegionRepository;
import com.example.demosecucume.service.HabitantService;
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
@RequestMapping("/Habitant")
@AllArgsConstructor
public class HabitantController {


    @Autowired
    private final HabitantService habitantService;


    @ApiOperation(value = "Juste pour ajouter le chiffre de la population pour l'API MaliTourist")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping("/createHabitant/{nom}")
    public Object create(@RequestBody Habitant habitant , @PathVariable String nom){
        return habitantService.creer(habitant,nom);

    }

    @ApiOperation(value = "Juste pour avoir la liste de tout les chiffres de population par date de l'API MaliTourist")
    @PreAuthorize("hasAuthority('SCOPE_USER') or hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/readHabitant")
    public List<Habitant> read(){
        return habitantService.lire();
    }

    @ApiOperation(value = "Juste pour modifier un chiffre de population de  l'API MaliTourist")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PutMapping("/updateHabitant/{id_habitant}")
    public Object update(@PathVariable Long id_habitant, @RequestBody Habitant habitant){
        return habitantService.modifier(id_habitant, habitant);
    }

    @ApiOperation(value = "Juste pour supprimer un chiffre de population de l'API MaliTourist")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/deleteHabitant/{id_habitant}")
    public Object delete(@PathVariable Long id_habitant){
        return habitantService.supprimer(id_habitant);
    }

    @GetMapping("/readHabitanttt/{idregion}")
    public List<Habitant> affichehabitant(Region idregion) {

        return habitantService.affichehabitant(idregion);
    }
}
