package com.example.demosecucume.service;

import com.example.demosecucume.Entities.Habitant;
import com.example.demosecucume.Entities.Region;
import com.example.demosecucume.Entities.massageError;
import com.example.demosecucume.Repository.HabitantRepository;
import com.example.demosecucume.Repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HabitantServiceImpl implements HabitantService {

    private final RegionRepository regionRepository;
    private final HabitantRepository habitantRepository;

    @Override
    public Object creer(Habitant habitant, String nom) {
        if (regionRepository.findByNomregion(nom) == null) {
            return massageError.ErreurResponse("cette région " + nom + " n'existe pas", HttpStatus.BAD_REQUEST, null);
        } else {

            Region region = regionRepository.findByNomregion(nom);
            habitant.setRegion(region);
            habitantRepository.save(habitant);
            return massageError.ErreurResponse("habitant ajouter avec succès", HttpStatus.OK, null);
        }


    }

    @Override
    public List<Habitant> lire() {
        return habitantRepository.findAll();
    }

/*    @Override
    public Habitant modifier(Long idhabitant, Habitant habitant) {
        return habitantRepository.findById(idhabitant)
                .map(habitant1 -> {
                    habitant1.setDate(habitant.getDate());
                    habitant1.setNbhabitant(habitant.getNbhabitant());
                    return habitantRepository.save(habitant1);
                }).orElseThrow(() -> new RuntimeException("Habitant non trouve !"));
    }*/

    @Override
    public Object modifier(Long idhabitant, Habitant habitant) {
        if (habitantRepository.findByIdhabitant(idhabitant) != null) {
            Habitant habitant1 = habitantRepository.findById(idhabitant).get();
            habitant1.setNbhabitant(habitant.getNbhabitant());
            habitant1.setDate(habitant.getDate());
            habitantRepository.saveAndFlush(habitant1);
            return massageError.ErreurResponse("habitant modifier avec succès", HttpStatus.OK, null);
        } else {
            return massageError.ErreurResponse("cette habitant n'existe pas ", HttpStatus.BAD_REQUEST, null);
        }
    }

    @Override
    public Object supprimer(long id_habitant) {
        if (habitantRepository.findByIdhabitant(id_habitant) != null) {
            habitantRepository.deleteById(id_habitant);
            return massageError.ErreurResponse("habitant supprimer avec succès", HttpStatus.OK, null);
        }
        return massageError.ErreurResponse("cette habitant n'existe pas ", HttpStatus.BAD_REQUEST, null);
    }
}
