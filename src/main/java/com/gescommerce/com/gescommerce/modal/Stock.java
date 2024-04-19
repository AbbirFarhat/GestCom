package com.gescommerce.com.gescommerce.modal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "article_name")
    private String articleName;

    @Column(name = "quantity")
    private int quantity;

   // @ManyToOne
    //private Article articles;
}
