package com.gescommerce.com.gescommerce.modal;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

// p.category.id --> category is an object, category.id is its attribute
@NamedQuery(name = "Article.getAllArticle", query = "select new com.gescommerce.com.gescommerce.wrapper.ArticleWrapper(p.id, p.name, p.codeArticle, p.designation,p.prixUnitaireHt,p.tauxTva,p.prixUnitaireTtc,p.photo, p.status, p.category.id, p.category.name) from Article p")
@NamedQuery(name = "Article.updateArticleStatus", query = "update Article p set p.status=:status where p.id=:id")
@NamedQuery(name = "Article.getArticleByCategory", query = "select new com.gescommerce.com.gescommerce.wrapper.ArticleWrapper(p.id, p.name) from Article p where p.category.id=:id and p.status='true'")
@NamedQuery(name = "Article.getArticleById", query = "select new com.gescommerce.com.gescommerce.wrapper.ArticleWrapper(p.id, p.name, p.codeArticle, p.designation,p.prixUnitaireHt,p.tauxTva,p.prixUnitaireTtc,p.photo) from Article p where p.id=:id")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "article")
public class Article implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    // many to one --> one row in a table is mapped to multiple rows in another table
    @ManyToOne(fetch = FetchType.LAZY) // lazy = fetch data when needed
    @JoinColumn(name = "category_fk", nullable = false) // connect to category table via foreign key
    private Category category;

    @Column(name = "code_article")
    private String codeArticle;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prix_unitaire_ht")
    private BigDecimal prixUnitaireHt;

    @Column(name = "taux_tva")
    private BigDecimal tauxTva;

    @Column(name = "prix_unitaire_ttc")
    private BigDecimal prixUnitaireTtc;

    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @Column(name = "status")
    private String status;

    @Column(name = "identreprise")
    private Integer idEntreprise;


    @OneToMany(mappedBy = "article")
    private List<LigneVente> ligneVentes;

    @OneToMany(mappedBy = "article")
    private List<LigneCommandeClient> ligneCommandeClients;

   // @OneToMany(mappedBy = "article")
    //private List<Stock> stock;

}
