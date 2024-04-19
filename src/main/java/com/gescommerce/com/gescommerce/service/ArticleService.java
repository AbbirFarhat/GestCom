package com.gescommerce.com.gescommerce.service;
import com.gescommerce.com.gescommerce.wrapper.ArticleWrapper;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface ArticleService {

    // adds new product to a category api
    ResponseEntity<String> addNewArticle(Map<String, String> requestMap);

    // retrieves all products api
    ResponseEntity<List<ArticleWrapper>> getAllArticle(String filterValue);

    // updates a product api
    ResponseEntity<String> updateArticle(Map<String, String> requestMap);

    // deletes a product api
    ResponseEntity<String> deleteArticle(Integer id);

    // updates a product's status api
    ResponseEntity<String> updateStatus(Map<String, String> requestMap);

    // retrieves products from a specified category api
    ResponseEntity<List<ArticleWrapper>> getByCategory(Integer id);

    // retrieves products by id api
    ResponseEntity<ArticleWrapper> getById(Integer id);

}
