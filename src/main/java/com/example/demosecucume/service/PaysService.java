package com.example.demosecucume.service;

import com.example.demosecucume.Entities.Pays;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaysService {

    Pays creer(Pays pays);

    List<Pays> lire();

    Object modifier(Long idpays, Pays pays);

    Object supprimer(Long idpays);
}
