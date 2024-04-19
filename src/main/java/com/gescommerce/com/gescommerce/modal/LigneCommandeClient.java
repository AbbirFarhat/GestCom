package com.gescommerce.com.gescommerce.modal;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "lignecommandeclient")
@Builder
//peut se référer à différentes choses en fonction du contexte, mais souvent, cela fait référence à une fonctionnalité ou une annotation utilisée dans la programmation, en particulier dans le langage Java.

public class LigneCommandeClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "idcommandeclient")
    private CommandeClient commandeClient;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "prixunitaire")
    private BigDecimal prixUnitaire;

    @Column(name = "identreprise")
    private Integer idEntreprise;
}
