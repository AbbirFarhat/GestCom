package com.gescommerce.com.gescommerce.dao;

import com.gescommerce.com.gescommerce.modal.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntrepriseDao extends JpaRepository<Entreprise, Long>  {


    Optional<Entreprise> findById(Long id);
}
