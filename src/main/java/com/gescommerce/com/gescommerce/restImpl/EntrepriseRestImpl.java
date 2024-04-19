package com.gescommerce.com.gescommerce.restImpl;

import com.gescommerce.com.gescommerce.modal.Entreprise;
import com.gescommerce.com.gescommerce.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("entreprises")
public class EntrepriseRestImpl {
    private final EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseRestImpl(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @PostMapping("add")
    public ResponseEntity<Entreprise> addEntreprise(@RequestBody Entreprise entreprise) {
        Entreprise savedEntreprise = entrepriseService.saveEntreprise(entreprise);
        return ResponseEntity.ok(savedEntreprise);
    }

    @GetMapping("{id}")
    public ResponseEntity<Entreprise> getEntrepriseById(@PathVariable Long id) {
        Entreprise entreprise = entrepriseService.getEntrepriseById(id);
        if (entreprise != null) {
            return ResponseEntity.ok(entreprise);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<Entreprise>> getAllEntreprises() {
        List<Entreprise> entreprises = entrepriseService.getAllEntreprises();
        return ResponseEntity.ok(entreprises);
    }

    @PutMapping("{id}")
    public ResponseEntity<Entreprise> updateEntreprise(@PathVariable Long id, @RequestBody Entreprise entreprise) {
        Entreprise updatedEntreprise = entrepriseService.updateEntreprise(id, entreprise);
        if (updatedEntreprise != null) {
            return ResponseEntity.ok(updatedEntreprise);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEntreprise(@PathVariable Long id) {
        entrepriseService.deleteEntreprise(id);
        String message = "Entreprise with ID " + id + " has been deleted successfully.";
        return ResponseEntity.ok(message);
    }
}

