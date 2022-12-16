package com.example.demosecucume.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Pays")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idpays;
    @Column(name = "nompays", unique = true)
    private String nompays;


    @ManyToOne
    private AppUser appUser;
}
