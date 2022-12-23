package com.example.demosecucume.Repository;

import com.example.demosecucume.Entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RegionRepository extends JpaRepository<Region, Long> {

    Region findByNomregion(String nom);

    Region findByIdregion(Long idregion);





    @Query(value = "SELECT Pays.nom_pays, Region.code_region, Region.nom, Region.lan_parle, Region.do_activite, Region.superficie, Habitant.date, Habitant.nb_habitant FROM Region, Pays, Habitant WHERE Region.id_region=Habitant.region_id_region and Pays.id_pays=Region.pays_id_pays",nativeQuery = true)
    public Iterable<Object[]> FIND_REGION_AVEC_PAYS();

    @Query(value = "SELECT Region.code_region, Region.nom, Region.lan_parle, Region.do_activite, Region.superficie, Habitant.date, Habitant.nb_habitant FROM Region, Habitant WHERE Region.id_region=Habitant.region_id_region",nativeQuery = true)
    public Iterable<Object[]> FIND_REGION_SANS_PAYS();


}
