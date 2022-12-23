package com.example.demosecucume.Controller;

import com.example.demosecucume.Entities.Pays;
import com.example.demosecucume.Entities.massageError;
import com.example.demosecucume.service.PaysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@Api(value = "hello", description = "Entité Pays de API MaliTourist")
@RestController
@RequestMapping("/Pays")
@AllArgsConstructor
public class PaysController {

    @Autowired
    private final PaysService paysService;

    @ApiOperation(value = "Juste pour ajouter un pays à l'API MaliTourist")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping("/create")
    public Object create(@RequestBody Pays pays) {
        try {
            paysService.creer(pays);
            return massageError.ErreurResponse("le Pays "+pays.getNompays()+" enregistrer avec succès", HttpStatus.OK, null);
        }catch (Exception e){
            return massageError.ErreurResponse("le Pays "+pays.getNompays()+" existe déjà", HttpStatus.NOT_FOUND, null);
        }
    }

    @ApiOperation(value = "Juste pour avoir la liste des pays de l'API MaliTourist")
   // @PreAuthorize("hasAuthority('SCOPE_USER') or hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/read")
    public List<Pays> read() {
        return paysService.lire();
    }

    @ApiOperation(value = "Juste pour modifier un pays de l'API MaliTourist")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PutMapping("/update/{id_pays}")
    public Object update(@PathVariable Long id_pays, @RequestBody Pays pays){

        return  paysService.modifier(id_pays, pays);
    }

    @ApiOperation(value = "Juste pour supprimer un pays de l'API MaliTourist")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/delete/{idpays}")
    public Object delete(@PathVariable Long idpays){

        return paysService.supprimer(idpays);
    }
}
