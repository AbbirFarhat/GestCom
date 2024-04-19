package com.gescommerce.com.gescommerce.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "factures")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder//peut se référer à différentes choses en fonction du contexte, mais souvent, cela fait référence à une fonctionnalité ou une annotation utilisée dans la programmation, en particulier dans le langage Java.

public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_facture")
    private String numeroFacture;

    @Column(name = "date_facture")
    private Instant dateFacture;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "facture")
    private List<LigneFacture> lignesFacture;



}
