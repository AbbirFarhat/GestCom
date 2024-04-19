package com.gescommerce.com.gescommerce.servicelmpl;


import com.gescommerce.com.gescommerce.dao.CommandeClientDao;
import com.gescommerce.com.gescommerce.modal.CommandeClient;
import com.gescommerce.com.gescommerce.service.CommandeClientService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeClientServiceImpl implements CommandeClientService {

    private final CommandeClientDao commandeClientDao;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientDao commandeClientDao) {
        this.commandeClientDao = commandeClientDao;
    }

    @Override
    public CommandeClient save(CommandeClient commandeClient) {
        return commandeClientDao.save(commandeClient);
    }

    @Override
    public CommandeClient findById(Long id) throws NotFoundException {
        Optional<CommandeClient> optionalCommandeClient = commandeClientDao.findById(id);
        if (optionalCommandeClient.isPresent()) {
            return optionalCommandeClient.get();
        } else {
            throw new NotFoundException("CommandeClient not found");
        }
    }

    @Override
    public List<CommandeClient> findAll() {
        return commandeClientDao.findAll();
    }

    @Override
    public void deleteById(Long id) {
        commandeClientDao.deleteById(id);
    }

    @Override
    public CommandeClient update(CommandeClient commandeClient, Long id) throws NotFoundException {
        if (commandeClientDao.existsById(id)) {
            commandeClient.setId(id);
            return commandeClientDao.save(commandeClient);
        } else {
            throw new NotFoundException("CommandeClient not found");
        }
    }
}

