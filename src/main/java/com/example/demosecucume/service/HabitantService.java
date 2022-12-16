package com.example.demosecucume.service;

import com.example.demosecucume.Entities.Habitant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HabitantService {

    Object creer(Habitant habitant, String nom);

    List<Habitant> lire();

    Object modifier(Long id_habitant, Habitant habitant);

    Object supprimer(long id_habitant);
}