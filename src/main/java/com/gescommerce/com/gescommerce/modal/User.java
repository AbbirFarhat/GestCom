package com.gescommerce.com.gescommerce.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NamedQuery(name = "User.findByEmailId", query = "select u from User u where u.email=:email")
@NamedQuery(name = "User.getAllUser", query = "select new com.gescommerce.com.gescommerce.wrapper.UserWrapper(u.id, u.nom, u.email, u.datedecreation, u.status) from User u where u.profil='user'")
@NamedQuery(name = "User.updateStatus", query = "update User u set u.status=:status where u.id=:id")
@NamedQuery(name = "User.getAllAdmin", query = "select u.email from User u where u.profil='admin'")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "user")

public class User implements Serializable {
    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "datedecreation")
    private String datedecreation;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private String status;

    @Column(name = "profil")
    private String profil;

    @ManyToOne
    @JoinColumn(name = "identreprise")
    private Entreprise entreprise;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private List<Roles> roles;
}
