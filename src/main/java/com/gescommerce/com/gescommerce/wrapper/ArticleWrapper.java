package com.gescommerce.com.gescommerce.wrapper;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class ArticleWrapper {
    private Integer id;
    private String name;
    private String codeArticle;
    private String designation;
    private BigDecimal prixUnitaireHt;
    private BigDecimal tauxTva;
    private BigDecimal prixUnitaireTtc;
    private byte[] photo;
    private String status;
    private Integer categoryId;
    private String categoryName;



    // empty constructor
    public  ArticleWrapper() {

    }

    // overloaded constructor
    public ArticleWrapper(Integer id, String name, String codeArticle, String designation, BigDecimal prixUnitaireHt, BigDecimal tauxTva, BigDecimal prixUnitaireTtc, byte[] photo, String status, Integer categoryId, String categoryName) {

        this.id = id;
        this.name = name;
        this.codeArticle = codeArticle;
        this.designation = designation;
        this.prixUnitaireHt = prixUnitaireHt;
        this.tauxTva = tauxTva;
        this.prixUnitaireTtc = prixUnitaireTtc;
        this.photo = photo;
        this.status = status;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    // overloaded constructor
    public ArticleWrapper(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // overloaded constructor
    public ArticleWrapper(Integer id, String name, String codeArticle, String designation, BigDecimal prixUnitaireHt, BigDecimal tauxTva, BigDecimal prixUnitaireTtc, byte[] photo) {
        this.id = id;
        this.name = name;
        this.codeArticle = codeArticle;
        this.designation = designation;
        this.prixUnitaireHt = prixUnitaireHt;
        this.tauxTva = tauxTva;
        this.prixUnitaireTtc = prixUnitaireTtc;
        this.photo = photo;
    }

}