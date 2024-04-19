package com.gescommerce.com.gescommerce.service;

import com.gescommerce.com.gescommerce.modal.CommandeClient;
import javassist.NotFoundException;

import java.util.List;

public interface CommandeClientService {
    CommandeClient save(CommandeClient commandeClient);
    CommandeClient findById(Long id) throws NotFoundException;
    List<CommandeClient> findAll();
    void deleteById(Long id);
    CommandeClient update(CommandeClient commandeClient, Long id) throws NotFoundException;

}
