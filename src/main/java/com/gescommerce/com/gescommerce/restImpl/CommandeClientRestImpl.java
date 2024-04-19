package com.gescommerce.com.gescommerce.restImpl;

import com.gescommerce.com.gescommerce.modal.Client;
import com.gescommerce.com.gescommerce.modal.CommandeClient;
import com.gescommerce.com.gescommerce.service.ClientService;
import com.gescommerce.com.gescommerce.service.CommandeClientService;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("commande-client")
public class CommandeClientRestImpl {
    private final CommandeClientService commandeClientService;
    private final ClientService clientService; // Injectez le service ClientService

    @Autowired
    public CommandeClientRestImpl(CommandeClientService commandeClientService, ClientService clientService) {
        this.commandeClientService = commandeClientService;
        this.clientService = clientService; // Initialiser le service ClientService
    }
    @SneakyThrows
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("add")
    public CommandeClient addCommandeClient(@RequestBody CommandeClient commandeClient) {
        // Vérifier si le client existe dans la base de données
        Client client = clientService.findById(commandeClient.getClient().getId());
        if (client != null) {
            // Le client existe, donc on peut ajouter la commande client
            return commandeClientService.save(commandeClient);
        } else {
            // Le client n'existe pas, renvoyer une erreur ou gérer la situation selon vos besoins
            throw new NotFoundException("Client non trouvé avec l'ID fourni.");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("{id}")
    public CommandeClient getCommandeClientById(@PathVariable Long id) throws NotFoundException {
        return commandeClientService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("all")
    public List<CommandeClient> getAllCommandeClients() {
        return commandeClientService.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCommandeClientById(@PathVariable Long id) {
        commandeClientService.deleteById(id);
        return ResponseEntity.ok("La commande a été supprimée avec succès.");
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("{id}")
    public CommandeClient updateCommandeClient(@PathVariable Long id, @RequestBody CommandeClient commandeClient) throws NotFoundException {
        return commandeClientService.update(commandeClient, id);
    }
}




