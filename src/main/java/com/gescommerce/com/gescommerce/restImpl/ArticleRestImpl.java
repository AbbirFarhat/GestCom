package com.gescommerce.com.gescommerce.restImpl;


import com.gescommerce.com.gescommerce.constants.CommerceConstants;
import com.gescommerce.com.gescommerce.dao.ArticleDao;
import com.gescommerce.com.gescommerce.rest.ArticleRest;
import com.gescommerce.com.gescommerce.service.ArticleService;
import com.gescommerce.com.gescommerce.utils.CommerceUtils;
import com.gescommerce.com.gescommerce.wrapper.ArticleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ArticleRestImpl implements ArticleRest {

    @Autowired
    ArticleService articleService;

    @Override
    public ResponseEntity<String> addNewArticle(Map<String, String> requestMap) {
        try {
            return articleService.addNewArticle(requestMap); //
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Autowired
    ArticleDao articleDao;

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
            return articleService.updateArticle(requestMap); // updates a product
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteArticle(Integer id) {
        try {
            return articleService.deleteArticle(id); // deletes a product
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
        try {
            return articleService.updateStatus(requestMap); // updates a product
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ArticleWrapper>> getByCategory(Integer id) {
        try {
            return articleService.getByCategory(id); // retrieves products within a certain category
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ArticleWrapper> getById(Integer id) {
        try {
            return articleService.getById(id); // retrieves products by id
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArticleWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
