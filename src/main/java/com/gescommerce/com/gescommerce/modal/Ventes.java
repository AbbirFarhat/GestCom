package com.gescommerce.com.gescommerce.modal;

import jakarta.persistence.*;

import lombok.*;

import java.time.Instant;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ventes")
@Builder
//peut se référer à différentes choses en fonction du contexte, mais souvent, cela fait référence à une fonctionnalité ou une annotation utilisée dans la programmation, en particulier dans le langage Java.

public class Ventes  extends AbstractEntity{
    @Column(name = "code")
    private String code;

    @Column(name = "datevente")
    private Instant dateVente;

    @Column(name = "commentaire")
    private String commentaire;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @OneToMany(mappedBy = "vente")
    private List<LigneVente> ligneVentes;

}
