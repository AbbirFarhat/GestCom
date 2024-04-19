package com.gescommerce.com.gescommerce.dao;

import com.gescommerce.com.gescommerce.modal.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDao extends JpaRepository<Client, Long> {
    Client findByNom(String nom);
}
