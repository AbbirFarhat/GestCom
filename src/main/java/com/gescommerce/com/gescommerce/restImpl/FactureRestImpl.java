package com.gescommerce.com.gescommerce.restImpl;

import com.gescommerce.com.gescommerce.modal.Facture;
import com.gescommerce.com.gescommerce.rest.FactureRest;
import com.gescommerce.com.gescommerce.service.FactureService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("factures")
public class FactureRestImpl implements FactureRest {
    private final FactureService factureService;

    @Autowired
    public FactureRestImpl(FactureService factureService) {
        this.factureService = factureService;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("add")
    public ResponseEntity<Facture> addFacture(@RequestBody Facture facture) {
        Facture savedFacture = factureService.save(facture);
        return ResponseEntity.ok(savedFacture);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<Facture> getFactureById(@PathVariable Long id) throws NotFoundException {
        Facture facture = factureService.findById(id);
        return ResponseEntity.ok(facture);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("all")
    public ResponseEntity<List<Facture>> getAllFactures() {
        List<Facture> factures = factureService.findAll();
        return ResponseEntity.ok(factures);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFactureById(@PathVariable Long id) {
        factureService.deleteById(id);
        String message = "La facture avec l'identifiant " + id + " a été supprimée avec succès.";
        return ResponseEntity.ok(message);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<Facture> updateFacture(@PathVariable Long id, @RequestBody Facture facture) throws NotFoundException {
        Facture updatedFacture = factureService.update(facture, id);
        return ResponseEntity.ok(updatedFacture);
    }
}
