package com.gescommerce.com.gescommerce.dao;

import com.gescommerce.com.gescommerce.modal.Client;
import com.gescommerce.com.gescommerce.modal.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface FournisseurDao extends JpaRepository<Fournisseur, Long> {
    Fournisseur findByNom(String nom);

}
