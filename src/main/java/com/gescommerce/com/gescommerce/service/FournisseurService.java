package com.gescommerce.com.gescommerce.service;

import com.gescommerce.com.gescommerce.modal.Client;
import com.gescommerce.com.gescommerce.modal.Fournisseur;

import java.util.List;

public interface FournisseurService {
    Fournisseur save( Fournisseur fournisseur);

    Fournisseur findById(Integer id);


    Fournisseur findByNom(String nom);

    void delete(Integer id);



    List< Fournisseur> findAll();

    Fournisseur update(Long id,  Fournisseur fournisseur);

    //Client update(Long id, Client client) throws NotFoundException;
}
