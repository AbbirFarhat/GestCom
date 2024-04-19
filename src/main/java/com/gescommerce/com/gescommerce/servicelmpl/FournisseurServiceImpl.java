package com.gescommerce.com.gescommerce.servicelmpl;
import com.gescommerce.com.gescommerce.dao.FournisseurDao;
import com.gescommerce.com.gescommerce.modal.Fournisseur;
import com.gescommerce.com.gescommerce.service.FournisseurService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FournisseurServiceImpl implements FournisseurService {
    private FournisseurDao fournisseurDao;
    private ModelMapper modelMapper;

    public FournisseurServiceImpl(ModelMapper modelMapper, FournisseurDao fournisseurDao) {
        this.fournisseurDao = fournisseurDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Fournisseur save(Fournisseur fournisseur) {
        // Mapping dufournisseur à l'entité Fournisseur
        fournisseur = modelMapper.map(fournisseur, Fournisseur.class);

        // Définition de la date de création à la date actuelle
        fournisseur.setCreationDate(new Date());

        // Sauvegarde du fournisseur dans la base de données
        Fournisseur savedFournisseur = fournisseurDao.save(fournisseur);

        // Mapping de l'entité sauvegardée à l'objet Client
        return modelMapper.map(savedFournisseur, Fournisseur.class);
    }

    @Override
    public Fournisseur findById(Integer id) {
        return null;
    }

    @Override
    public Fournisseur findByNom(String nom) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Fournisseur> findAll() {
        return fournisseurDao.findAll()
                .stream().map(el -> modelMapper.map(el, Fournisseur.class))
                .collect(Collectors.toList());
    }

    @Override
    public Fournisseur update(Long id, Fournisseur fournisseur) {
        return null;
    }
/**
 @Override
 public Client findById(Integer id) {
 Client client = clientDao.findById(Long.valueOf(id)).orElseThrow(() -> new RuntimeException("Client not found"));
 return modelMapper.map(client, Client.class);
 }
 @Override
 public Client findByNom(String nom) {
 Client client = clientDao.findByNom(nom);
 return modelMapper.map(client, Client.class);
 }
 @Override
 public void delete(Integer id) {
 clientDao.deleteById(Long.valueOf(id));
 }

 @Override
 public Client update(Long id, Client client)  {
 Optional<Client> clientOptional = clientDao.findById(Long.valueOf(id));

 client = modelMapper.map(client, Client.class);
 client.setId(Math.toIntExact(id));
 Client updated = clientDao.save(client);
 return modelMapper.map(updated, Client.class);

 }
 */
}
