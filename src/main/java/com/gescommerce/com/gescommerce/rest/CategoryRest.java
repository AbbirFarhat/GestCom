package com.gescommerce.com.gescommerce.rest;

import com.gescommerce.com.gescommerce.modal.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RequestMapping(path = "/category")
public interface  CategoryRest {

    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewCategory(@RequestBody(required = true) Map<String, String> requestMap);
   // @PostMapping(path = "/add")
    //ResponseEntity<String> createClient(Map<String, String> requestMap);

    @GetMapping(path = "/get")
    public ResponseEntity<List<Category>> getAllCategory(@RequestParam(required = false) String filterValue);

    @PostMapping(path = "/update")
    public ResponseEntity<String> updateCategory(@RequestBody(required = true) Map<String, String> requestMap);
}
