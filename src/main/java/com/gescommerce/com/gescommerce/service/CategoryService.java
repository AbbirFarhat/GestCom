package com.gescommerce.com.gescommerce.service;

import com.gescommerce.com.gescommerce.modal.Category;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface CategoryService {
    // adds new category to the category api
    ResponseEntity<String> addNewCategory(Map<String, String> requestMap);

    // retrieves all categories api
    ResponseEntity<List<Category>> getAllCategory(String filterValue);

    // updates category api
    ResponseEntity<String> updateCategory(Map<String, String> requestMap);
}