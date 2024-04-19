package com.gescommerce.com.gescommerce.restImpl;
import com.gescommerce.com.gescommerce.modal.Client;
import com.gescommerce.com.gescommerce.service.ClientService;
import jakarta.validation.Valid;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClientRestImpl {
    private ClientService clientService;

    public ClientRestImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") // Exige que l'utilisateur ait le rôle ROLE_ADMIN pour accéder à cette méthode
    @PostMapping("save")
    public Client save(@RequestBody() Client client) {
        return clientService.save(client);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')") // Exige que l'utilisateur ait le rôle ROLE_ADMIN pour accéder à cette méthode
    @GetMapping("get")
    public List<Client> getClient() {
        return clientService.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") // Exige que l'utilisateur ait le rôle ROLE_ADMIN pour accéder à cette méthode
    @GetMapping("getByNom")
    public Client findByNom(@PathVariable String nom) {
        return clientService.findByNom(nom);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')") // Exige que l'utilisateur ait le rôle ROLE_ADMIN pour accéder à cette méthode
    @DeleteMapping("deleteByid")
    public void delete(@PathVariable Integer id) {
        clientService.delete(id);
    }

    /**
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("updateByid/{id}") // Ajoutez {id} pour récupérer l'ID à partir de l'URL
    public ResponseEntity<Client> update(@PathVariable Integer id, @Valid @RequestBody Client client) throws NotFoundException {
        Client updatedClient = clientService.update(client, id); // Appelez la méthode de service pour mettre à jour le client
        return ResponseEntity.ok(updatedClient); // Renvoyer la réponse avec le client mis à jour et le statut 200 OK
    }*/
/**
    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client client) {
        try {
            Client updatedClient = clientService.update(id, client);
            return ResponseEntity.ok(updatedClient);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }*/




}

