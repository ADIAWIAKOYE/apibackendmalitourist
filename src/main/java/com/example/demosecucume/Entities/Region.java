package com.example.demosecucume.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Region")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idregion;

    private String coderegion;
    @Column(unique = true)
    private String nomregion;
    private String doactivite;
    private String lanparle;
    private String superficie;
    private String imageregion;



    @ManyToOne
    private Pays pays;

}
