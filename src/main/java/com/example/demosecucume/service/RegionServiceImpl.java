package com.example.demosecucume.service;

import com.example.demosecucume.Entities.Pays;
import com.example.demosecucume.Entities.Region;
import com.example.demosecucume.Entities.massageError;
import com.example.demosecucume.Repository.PaysRepository;
import com.example.demosecucume.Repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegionServiceImpl implements RegionService{

    private final RegionRepository regionRepository;
    private final PaysRepository paysRepository;

    @Override
    public Object creer(Region region, String nompays) {
        if (paysRepository.findByNompays(nompays) == null){
            return massageError.ErreurResponse("Cet pays n'existe pas", HttpStatus.BAD_REQUEST, null);
        }else {
            Pays pays = paysRepository.findByNompays(nompays);
            region.setPays(pays);
            if (regionRepository.findByNomregion(region.getNomregion()) == null){
                regionRepository.save(region);
                return massageError.ErreurResponse("region enregistre avec succes", HttpStatus.OK, null);
            }
            return massageError.ErreurResponse("cet region exite déjà", HttpStatus.BAD_REQUEST, null);
        }



    }



    @Override
    public List<Region> lire() {

        return regionRepository.findAll();
    }

    @Override
    public Region afficher(Long idregion) {

        return regionRepository.findById(idregion).get();
    }

    public Region afficherr(Long idregion) {

        return regionRepository.findById(idregion).get();
    }

    @Override
    public Iterable<Object[]> lireFIND_REGION_AVEC_PAYS() {

        return  regionRepository.FIND_REGION_AVEC_PAYS();
    }

    @Override
    public Iterable<Object[]> lireFIND_REGION_SANS_PAYS() {

        return  regionRepository.FIND_REGION_SANS_PAYS();
    }


    /*   @Override
       public Region modifier(long id_region, Region region) {
           return regionRepository.findById(id_region)
                   .map(region1 -> {
                       region1.setCoderegion(region.getCoderegion());
                       region1.setDoactivite(region.getDoactivite());
                       region1.setLanparle(region.getLanparle());
                       region1.setNom(region.getNom());
                       region1.setSuperficie(region.getSuperficie());
                       return regionRepository.save(region1);
                   }).orElseThrow(() -> new RuntimeException("Region non trouver !"));
       }*/
    @Override
    public Object modifier(long idregion, Region region) {
        if (regionRepository.findByIdregion(idregion) != null ){
            Region region1 = regionRepository.findById(idregion).get();
            region1.setCoderegion(region.getCoderegion());
            region1.setNomregion(region.getNomregion());
            region1.setDoactivite(region.getDoactivite());
            region1.setLanparle(region.getLanparle());
            region1.setSuperficie(region.getSuperficie());
            region1.setDescription(region.getDescription());
            regionRepository.saveAndFlush(region1);
            return massageError.ErreurResponse("region modifier avec succes", HttpStatus.OK, null);
        }
        return massageError.ErreurResponse("region nom trouvez", HttpStatus.BAD_REQUEST, null);
    }


    @Override
    public Object supprimer(long id_region) {
        if (regionRepository.findByIdregion(id_region) != null){
            regionRepository.deleteById(id_region);
            return massageError.ErreurResponse("region supprimer avec succes", HttpStatus.OK, null);
        }else {
            return massageError.ErreurResponse("region nom trouvez", HttpStatus.BAD_REQUEST, null);
        }

    }
}
