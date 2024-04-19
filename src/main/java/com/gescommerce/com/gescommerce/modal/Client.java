package com.gescommerce.com.gescommerce.modal;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
@Builder
//peut se référer à différentes choses en fonction du contexte, mais souvent, cela fait référence à une fonctionnalité ou une annotation utilisée dans la programmation, en particulier dans le langage Java.
@Getter
@Setter
public class Client implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "nom")
    private String nom;

    @Embedded
    private Adresse adresse;

    @Column(name = "mail")
    private String mail;

    @Column(name = "numTel")
    private String numTel;

    @Column(name = "id_entreprise")
    private Long idEntreprise;

    // Définir la relation avec CommandeClient
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommandeClient> commandeClients;

    // Autres attributs et méthodes de la classe
}