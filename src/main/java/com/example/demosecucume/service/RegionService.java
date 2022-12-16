package com.example.demosecucume.service;

import com.example.demosecucume.Entities.Region;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegionService {

    Object creer(Region region, String nompays);


    List<Region> lire();

    Iterable<Object[]>lireFIND_REGION_AVEC_PAYS();

    Iterable<Object[]>lireFIND_REGION_SANS_PAYS();

    Object modifier(long idregion, Region region);

    Object supprimer(long id_region);
}
