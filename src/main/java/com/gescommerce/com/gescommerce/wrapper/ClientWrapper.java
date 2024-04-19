package com.gescommerce.com.gescommerce.wrapper;

import com.gescommerce.com.gescommerce.modal.Adresse;
import com.gescommerce.com.gescommerce.modal.Client;
import lombok.Data;

@Data
public class ClientWrapper {
    private Integer id;
    private String nom;
    private String prenom;
    private Adresse adresse;
    private String photo;
    private String mail;
    private String numTel;
    private Integer idEntreprise;

        // Constructeurs
        public ClientWrapper() {}

        public ClientWrapper(Integer id ,String nom,String prenom,Adresse adresse,String photo,String mail,String numTel,Integer idEntreprise ) {
            this.id=id;
            this.nom=nom;
            this.prenom=prenom;
            this.adresse=adresse;
            this.photo=photo;
            this.mail=mail;
            this.numTel=numTel;
            this.idEntreprise=idEntreprise;

        }
    public ClientWrapper(Integer id ,String nom) {
        this.id=id;
        this.nom=nom;
    }
    public ClientWrapper(Integer id ,String nom,String prenom,Adresse adresse,String photo,String mail,String numTel ) {
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.photo=photo;
        this.mail=mail;
        this.numTel=numTel;

    }


}
