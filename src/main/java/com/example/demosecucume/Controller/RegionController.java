package com.example.demosecucume.Controller;

import com.example.demosecucume.Entities.Region;
import com.example.demosecucume.Repository.PaysRepository;
import com.example.demosecucume.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "hello", description = " Entité Region de API MaliTourist")
@RestController
@RequestMapping("/region")
@AllArgsConstructor
public class RegionController {

    @Autowired
    private final RegionService regionService;

    private final PaysRepository paysRepository;

    @ApiOperation(value = "Juste pour ajouter un région l'API MaliTourist")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PostMapping("/createregion/{nompays}")
    public Object create(@RequestBody Region region, @PathVariable String nompays){
       /* if (paysRepository.findByNompays(nompays) == null){
            return massageError.ErreurResponse("le pays "+nompays+" n'existe pas", HttpStatus.OK, null);
        }else{
            try {*/
        return regionService.creer(region,nompays);
           /* }catch (Exception e){
                return massageError.ErreurResponse("la Region "+region.getNom()+" existe déjà", HttpStatus.OK, null);
            }
        }*/


    }

    @ApiOperation(value = "Juste pour afficher la liste des régions de l'API MaliTourist")
    @PreAuthorize("hasAuthority('SCOPE_USER') or hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/readregion")
    public List<Region> read(){
        return regionService.lire();
    }

    @ApiOperation(value = "Juste pour modifier une region de l'API MaliTourist")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @PutMapping("/updateregion")
    public Object update(@PathVariable Long id_region, @RequestBody Region region){
        // try {
        return regionService.modifier(id_region, region);
        /*}catch (Exception e){

            return massageError.ErreurResponse("cet Region n'existe pas", HttpStatus.OK, null);
        }*/


    }

    @ApiOperation(value = "Juste pour supprimer une region de  l'API MaliTourist")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/deleteregion/{id_region}")
    public Object delete(@PathVariable Long id_region){

        return regionService.supprimer(id_region);
    }

    @ApiOperation(value = "Juste afficher les regions en précisant le pays de  l'API MaliTourist")
    @PreAuthorize("hasAuthority('SCOPE_USER') or hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/listeRegionAvecPays")
    public Iterable<Object[]> lireFIND_REGION_AVEC_PAYS(){

        return regionService.lireFIND_REGION_AVEC_PAYS();
    }

    @ApiOperation(value = "Juste afficher les regions sans précisant le pays de  l'API MaliTourist")
    @PreAuthorize("hasAuthority('SCOPE_USER') or hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/listeRegionSansPays")
    public Iterable<Object[]> lireFIND_REGION_SANS_PAYS(){

        return regionService.lireFIND_REGION_SANS_PAYS();
    }


}
