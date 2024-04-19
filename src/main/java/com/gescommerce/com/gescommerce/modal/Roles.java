package com.gescommerce.com.gescommerce.modal;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "roles")
@Builder
//peut se référer à différentes choses en fonction du contexte, mais souvent, cela fait référence à une fonctionnalité ou une annotation utilisée dans la programmation, en particulier dans le langage Java.

public class Roles {

    @Id
    private Long id;

    @Column(name = "rolename")
    private String roleName;

    @ManyToOne
    @JoinColumn(name = "iduser")
    private User user;

}
