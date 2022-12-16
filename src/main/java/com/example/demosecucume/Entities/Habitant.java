package com.example.demosecucume.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Habitant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Habitant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idhabitant;
    private Date date;
    private String nbhabitant;


    @ManyToOne
    private Region region;

}