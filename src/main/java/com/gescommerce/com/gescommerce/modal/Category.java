package com.gescommerce.com.gescommerce.modal;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serializable;
import java.util.List;

@NamedQuery(name = "Category.getAllCategory", query = "select c from Category c ")

@Data
@Entity
@DynamicInsert
@DynamicUpdate

@Table(name = "category")
public class Category implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;

    @Column(name = "designation")
    private String designation;

    @Column(name = "identreprise")
    private Integer idEntreprise;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;
}
