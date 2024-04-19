package com.gescommerce.com.gescommerce.modal;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "entreprise")
@Data
//peut se référer à différentes choses en fonction du contexte, mais souvent, cela fait référence à une fonctionnalité ou une annotation utilisée dans la programmation, en particulier dans le langage Java.

public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "code_societe")
    private String codeSociete;

    @Column(name = "raison_social")
    private String raisonSocial;

    @Column(name = "raison_social_arabe")
    private String raisonSocialArabe;

    @Column(name = "registre_commerce")
    private String registreCommerce;

    @Column(name = "num_tel1")
    private String numTel1;

    @Column(name = "num_tel2")
    private String numTel2;

    @Column(name = "num_fax")
    private String numFax;

    @Column(name = "email")
    private String email;

    @Column(name = "site_web")
    private String siteWeb;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "adresse_arabe")
    private String adresseArabe;

    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "cachet_signature")
    private byte[] cachetSignature;

    @Column(name = "qr_code")
    private byte[] qrCode;

    @OneToMany(mappedBy = "entreprise")
    private List<User> user;

    // Getters and setters
}
