package com.gescommerce.com.gescommerce.servicelmpl;

import com.gescommerce.com.gescommerce.dao.FactureDao;
import com.gescommerce.com.gescommerce.modal.Facture;
import com.gescommerce.com.gescommerce.service.FactureService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public  class FactureServiceImpl  implements FactureService {
    private final FactureDao factureDao;

    @Autowired
    public FactureServiceImpl(FactureDao factureRepository) {
        this.factureDao = factureRepository;
    }

    @Override
    public Facture save(Facture facture) {
        return factureDao.save(facture);
    }

    @Override
    public Facture findById(Long id) throws NotFoundException {
        Optional<Facture> optionalFacture = factureDao.findById(Math.toIntExact(id));
        if (optionalFacture.isPresent()) {
            return optionalFacture.get();
        }
        throw new NotFoundException("Facture not found");
    }

    @Override
    public void deleteById(Long id) {
        factureDao.deleteById(Math.toIntExact(id));
    }

    @Override
    public List<Facture> findAll() {
        return factureDao.findAll();
    }

    @Override
    public Facture update(Facture facture, Long id) throws NotFoundException {
        if (factureDao.existsById(Math.toIntExact(id))) {
            facture.setId(id);
            return factureDao.save(facture);
        }
        throw new NotFoundException("Facture not found");
    }
}