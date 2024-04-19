package com.gescommerce.com.gescommerce.restImpl;

import com.gescommerce.com.gescommerce.modal.Client;
import com.gescommerce.com.gescommerce.modal.Fournisseur;
import com.gescommerce.com.gescommerce.service.ClientService;
import com.gescommerce.com.gescommerce.service.FournisseurService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fournisseur")
public class FournisseurRestImpl {
    private FournisseurService fournisseurService;

    public FournisseurRestImpl(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }



    @PreAuthorize("hasRole('ROLE_ADMIN')") // Exige que l'utilisateur ait le rôle ROLE_ADMIN pour accéder à cette méthode
    @PostMapping("save")
    public Fournisseur save(@RequestBody() Fournisseur fournisseur) {
        return fournisseurService.save(fournisseur);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')") // Exige que l'utilisateur ait le rôle ROLE_ADMIN pour accéder à cette méthode
    @GetMapping("get")
    public List<Fournisseur> getFournisseur() {
        return fournisseurService.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") // Exige que l'utilisateur ait le rôle ROLE_ADMIN pour accéder à cette méthode
    @GetMapping("getByNom")
    public Fournisseur findByNom(@PathVariable String nom) {
        return fournisseurService.findByNom(nom);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')") // Exige que l'utilisateur ait le rôle ROLE_ADMIN pour accéder à cette méthode
    @DeleteMapping("deleteByid")
    public void delete(@PathVariable Integer id) {
        fournisseurService.delete(id);
    }

}
