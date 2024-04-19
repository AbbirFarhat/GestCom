package com.gescommerce.com.gescommerce.servicelmpl;
import com.gescommerce.com.gescommerce.dao.ClientDao;
import com.gescommerce.com.gescommerce.modal.Client;
import com.gescommerce.com.gescommerce.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {
    private ClientDao clientDao;
    private ModelMapper modelMapper;

    public ClientServiceImpl(ClientDao clientDao, ModelMapper modelMapper) {
        this.clientDao = clientDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Client save(Client client) {
        // Mapping du client à l'entité Client
        client = modelMapper.map(client, Client.class);

        // Définition de la date de création à la date actuelle
        client.setCreationDate(new Date().toInstant());

        // Sauvegarde du client dans la base de données
        Client savedClient = clientDao.save(client);

        // Mapping de l'entité sauvegardée à l'objet Client
        return modelMapper.map(savedClient, Client.class);
    }

    @Override
    public Client findById(Integer id) {
        return null;
    }

    @Override
    public Client findByNom(String nom) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Client> findAll() {
        return clientDao.findAll()
                .stream().map(el -> modelMapper.map(el, Client.class))
                .collect(Collectors.toList());
    }

    @Override
    public Client update(Long id, Client client) {
        return null;
    }

    @Override
    public Client findById(Long id) {
        Client client = clientDao.findById(Long.valueOf(id)).orElseThrow(() -> new RuntimeException("Client not found"));
        return modelMapper.map(client, Client.class);
    }
/**
    @Override
    public Client findById(Integer id) {
        Client client = clientDao.findById(Long.valueOf(id)).orElseThrow(() -> new RuntimeException("Client not found"));
        return modelMapper.map(client, Client.class);
    }
    @Override
    public Client findByNom(String nom) {
        Client client = clientDao.findByNom(nom);
        return modelMapper.map(client, Client.class);
    }
    @Override
    public void delete(Integer id) {
        clientDao.deleteById(Long.valueOf(id));
    }

    @Override
    public Client update(Long id, Client client)  {
        Optional<Client> clientOptional = clientDao.findById(Long.valueOf(id));

        client = modelMapper.map(client, Client.class);
            client.setId(Math.toIntExact(id));
            Client updated = clientDao.save(client);
            return modelMapper.map(updated, Client.class);

    }
*/

}

