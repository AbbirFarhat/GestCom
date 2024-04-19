package com.gescommerce.com.gescommerce.modal;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "commande_client")
@Builder
//peut se référer à différentes choses en fonction du contexte, mais souvent, cela fait référence à une fonctionnalité ou une annotation utilisée dans la programmation, en particulier dans le langage Java.

public class CommandeClient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "code")
    private String code;

    @Column(name = "date_commande")
    private Instant dateCommande;

    @Column(name = "id_entreprise")
    private Long idEntreprise;

    // Définir la relation avec Client
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    // Autres attributs et méthodes de la classe
}