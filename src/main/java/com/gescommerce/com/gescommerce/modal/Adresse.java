package com.gescommerce.com.gescommerce.modal;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Data
@Builder//peut se référer à différentes choses en fonction du contexte, mais souvent, cela fait référence à une fonctionnalité ou une annotation utilisée dans la programmation, en particulier dans le langage Java.

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "adresse")


public class Adresse {

    @Column(name = "adresse1")
    private String adresse1;

    @Column(name = "adresse2")
    private String adresse2;

    @Column(name = "ville")
    private String ville;

    @Column(name = "code_postale")
    private String codePostale;

    @Column(name = "pays")
    private String pays;

    // Getters and setters
}
