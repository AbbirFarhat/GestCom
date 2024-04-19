package com.gescommerce.com.gescommerce.modal;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fournisseur")
@Builder
//peut se référer à différentes choses en fonction du contexte, mais souvent, cela fait référence à une fonctionnalité ou une annotation utilisée dans la programmation, en particulier dans le langage Java.
@Getter
@Setter
public class Fournisseur {
    @Id()
    @GeneratedValue
    private Integer id;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;


    @Column(name = "nom")
    private String nom;

    @Embedded
    private Adresse adresse;

    @Column(name = "mail")
    private String mail;

    @Column(name = "numTel")
    private String numtTel;

    @Column(name = "identreprise")
    private Integer idEntreprise;
}
