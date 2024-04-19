package com.gescommerce.com.gescommerce.modal;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ligneVente")

//peut se référer à différentes choses en fonction du contexte, mais souvent, cela fait référence à une fonctionnalité ou une annotation utilisée dans la programmation, en particulier dans le langage Java.

public class LigneVente  extends  AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "idvente")
    private Ventes vente;

    @ManyToOne
    @JoinColumn(name = "idarticle")
    private Article article;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "prixunitaire")
    private BigDecimal prixUnitaire;

    @Column(name = "identreprise")
    private Integer idEntreprise;




}
