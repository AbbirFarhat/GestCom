package com.gescommerce.com.gescommerce.servicelmpl;

import com.gescommerce.com.gescommerce.JWT.JwtFilter;
import com.gescommerce.com.gescommerce.constants.CommerceConstants;
import com.gescommerce.com.gescommerce.dao.ArticleDao;
import com.gescommerce.com.gescommerce.modal.Category;
import com.gescommerce.com.gescommerce.modal.Article;
import com.gescommerce.com.gescommerce.service.ArticleService;
import com.gescommerce.com.gescommerce.utils.CommerceUtils;
import com.gescommerce.com.gescommerce.wrapper.ArticleWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j // Annotation pour le logging
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> addNewArticle(Map<String, String> requestMap) {
        try {
            // Vérifie si l'utilisateur est un administrateur pour ajouter un produit
            if (jwtFilter.isAdmin()) {
                // Valide les données du produit
                if (validateArticleMap(requestMap, false)) {
                    // Ajoute le produit à la base de données
                    articleDao.save(getArticleFromMap(requestMap, false));
                    return CommerceUtils.getResponseEntity(CommerceConstants.PRODUCT_ADDED, HttpStatus.OK);
                }
                return CommerceUtils.getResponseEntity(CommerceConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else {
                return CommerceUtils.getResponseEntity(CommerceConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Valide les données du produit
    private boolean validateArticleMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    // Crée un objet Article à partir de la carte de requête
    private Article getArticleFromMap(Map<String, String> requestMap, boolean isAdd) {
        Category category = new Category();
        category.setId(Integer.parseInt(requestMap.get("categoryId"))); // Récupère l'ID de catégorie comme clé étrangère

        Article article = new Article();

        // Définit les attributs du produit
        if (isAdd) {
            article.setId(Integer.parseInt(requestMap.get("id")));
        } else {
            article.setStatus("true"); // Utilisez une chaîne pour représenter le statut
        }
        article.setCategory(category);
        article.setName(requestMap.get("name"));
        article.setCodeArticle(requestMap.get("codeArticle"));
        article.setDesignation(requestMap.get("designation"));
        article.setPrixUnitaireHt(new BigDecimal(requestMap.get("prixUnitaireHt")));
        article.setTauxTva(new BigDecimal(requestMap.get("tauxTva")));
        article.setPrixUnitaireTtc(new BigDecimal(requestMap.get("prixUnitaireTtc")));
        article.setPhoto(requestMap.get("photo").getBytes());

        return article;
    }


    // Méthode pour obtenir tous les produits
    @Override
    public ResponseEntity<List<ArticleWrapper>> getAllArticle(String filterValue) {
        try {
            return new ResponseEntity<List<ArticleWrapper>>(articleDao.getAllArticle(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<ArticleWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateArticle(Map<String, String> requestMap) {
        try {
            // Votre logique de mise à jour de l'article ici
            return CommerceUtils.getResponseEntity(CommerceConstants.PRODUCT_UPDATED, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteArticle(Integer id) {
        try {
            // Logique de suppression de l'article en utilisant l'ID
            // articleDao.deleteById(id);
            return CommerceUtils.getResponseEntity(CommerceConstants.PRODUCT_DELETED, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
        try {
            if (requestMap.containsKey("id") && requestMap.containsKey("status")) {
                int id = Integer.parseInt(requestMap.get("id"));
                boolean status = Boolean.parseBoolean(requestMap.get("status"));

                Optional<Article> optionalArticle = articleDao.findById(id);
                if (optionalArticle.isPresent()) {
                    Article article = optionalArticle.get();
                    article.setStatus(String.valueOf(status)); // Convertir le boolean en String
                    articleDao.save(article);
                    return CommerceUtils.getResponseEntity(CommerceConstants.PRODUCT_STATUS_UPDATED, HttpStatus.OK);
                } else {
                    return CommerceUtils.getResponseEntity(CommerceConstants.INVALID_PRODUCT, HttpStatus.NOT_FOUND);
                }
            } else {
                return CommerceUtils.getResponseEntity(CommerceConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<List<ArticleWrapper>> getByCategory(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<ArticleWrapper> getById(Integer id) {
        return null;
    }

    // Autres méthodes pour la mise à jour, la suppression et la récupération des produits
    // ...

}
