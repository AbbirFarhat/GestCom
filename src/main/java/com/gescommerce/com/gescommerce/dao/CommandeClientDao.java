package com.gescommerce.com.gescommerce.dao;

import com.gescommerce.com.gescommerce.modal.CommandeClient;
import com.gescommerce.com.gescommerce.modal.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeClientDao extends JpaRepository <CommandeClient, Long>{

}
