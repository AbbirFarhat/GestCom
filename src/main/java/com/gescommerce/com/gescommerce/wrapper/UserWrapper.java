package com.gescommerce.com.gescommerce.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.gescommerce.com.gescommerce.modal.User;

@Data
@NoArgsConstructor
public class UserWrapper {
    private Integer id;
    private String nom; // Correspond au champ 'nom' de la classe User
    private String email;
    private String datedecreation; // Correspond au champ 'datedecreation' de la classe User
    private String status;

    public UserWrapper(Integer id, String nom, String email, String datedecreation, String status) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.datedecreation = datedecreation;
        this.status = status;
    }

    // Méthode utilitaire pour convertir un objet User en UserWrapper
    public static UserWrapper fromUser(User user) {
        return new UserWrapper(user.getId(), user.getNom(), user.getEmail(), user.getDatedecreation(), user.getStatus());
    }
}
