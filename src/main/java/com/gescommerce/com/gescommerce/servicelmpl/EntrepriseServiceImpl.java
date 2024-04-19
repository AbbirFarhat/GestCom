package com.gescommerce.com.gescommerce.servicelmpl;

import com.gescommerce.com.gescommerce.dao.EntrepriseDao;
import com.gescommerce.com.gescommerce.modal.Entreprise;
import com.gescommerce.com.gescommerce.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EntrepriseServiceImpl implements EntrepriseService {
    private final EntrepriseDao entrepriseDao;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseDao entrepriseDao) {
        this.entrepriseDao= entrepriseDao;
    }

    @Override
    public Entreprise saveEntreprise(Entreprise entreprise) {
        return entrepriseDao.save(entreprise);
    }

    @Override
    public Entreprise getEntrepriseById(Long id) {
        Optional<Entreprise> optionalEntreprise = entrepriseDao.findById(id);
        return optionalEntreprise.orElse(null);
    }

    @Override
    public List<Entreprise> getAllEntreprises() {
        return entrepriseDao.findAll();
    }

    @Override
    public Entreprise updateEntreprise(Long id, Entreprise entreprise) {
        Optional<Entreprise> optionalEntreprise = entrepriseDao.findById(id);
        if (optionalEntreprise.isPresent()) {
            Entreprise existingEntreprise = optionalEntreprise.get();
            existingEntreprise.setNom(entreprise.getNom());
            existingEntreprise.setAdresse(entreprise.getAdresse());
            return entrepriseDao.save(existingEntreprise);
        }
        return null;
    }

    @Override
    public void deleteEntreprise(Long id) {
       entrepriseDao.deleteById(id);
    }
}