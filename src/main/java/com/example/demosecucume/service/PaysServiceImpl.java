package com.example.demosecucume.service;

import com.example.demosecucume.Entities.Pays;
import com.example.demosecucume.Entities.massageError;
import com.example.demosecucume.Repository.PaysRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaysServiceImpl implements PaysService{

    private final PaysRepository paysRepository;

    @Override
    public Pays creer(Pays pays) {

        return paysRepository.save(pays);
    }

    @Override
    public List<Pays> lire() {
        return paysRepository.findAll();
    }

 /*   @Override
    public Pays modifier(Long id_pays, Pays pays) {
        return paysRepository.findById(id_pays)
                .map(pays1 -> {
                    pays1.setNompays(pays.getNompays());
                    return paysRepository.save(pays1);
                }).orElseThrow(() -> new RuntimeException("Pay non trouvé !"));
    }*/

    @Override
    public Object modifier(Long idpays, Pays pays) {
        if (paysRepository.findByIdpays(idpays) !=null){
            Pays pays1 = paysRepository.findById(idpays).get();
            pays1.setNompays(pays.getNompays());
            paysRepository.saveAndFlush(pays1);
            return massageError.ErreurResponse(" cet Pays modifier avec succès", HttpStatus.OK,null);

        }else {
            return massageError.ErreurResponse(" cet Pays n'existe pas", HttpStatus.BAD_REQUEST,null);
        }
    }



    @Override
    public Object supprimer(Long idpays) {
        if (paysRepository.findByIdpays(idpays) != null){
            paysRepository.deleteById(idpays);
            return massageError.ErreurResponse("le Pays est supprimer avec succes", HttpStatus.OK, null);
            // return "Pay Supprimer !";
        }else {
            return massageError.ErreurResponse("le Pays n'existe pas", HttpStatus.BAD_REQUEST, null);
        }

    }
}
