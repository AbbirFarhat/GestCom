package com.gescommerce.com.gescommerce.service;

import com.gescommerce.com.gescommerce.modal.Client;
import javassist.NotFoundException;

import java.util.List;

public interface ClientService {
    Client save(Client client);


    Client findById(Integer id);

    Client findByNom(String nom);

    void delete(Integer id);



    List<Client> findAll();

    Client update(Long id, Client client);

    Client findById(Long id);

    //Client update(Long id, Client client) throws NotFoundException;
}
