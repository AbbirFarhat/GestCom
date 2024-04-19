package com.gescommerce.com.gescommerce.dao;

import com.gescommerce.com.gescommerce.modal.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureDao extends JpaRepository<Facture, Integer> {
}
