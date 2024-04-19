package com.gescommerce.com.gescommerce.service;

import com.gescommerce.com.gescommerce.modal.Facture;
import javassist.NotFoundException;

import java.util.List;

public interface FactureService {
    Facture save(Facture facture);
    Facture findById(Long id) throws NotFoundException;
    void deleteById(Long id);
    List<Facture> findAll();
    Facture update(Facture facture, Long id) throws NotFoundException;
}
