package com.gescommerce.com.gescommerce.service;
import com.gescommerce.com.gescommerce.modal.Entreprise;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EntrepriseService {


    Entreprise saveEntreprise(Entreprise entreprise);

    Entreprise getEntrepriseById(Long id);

    List<Entreprise> getAllEntreprises();

    Entreprise updateEntreprise(Long id, Entreprise entreprise);

    void deleteEntreprise(Long id);


    }


