package com.gescommerce.com.gescommerce.rest;
import com.gescommerce.com.gescommerce.wrapper.ArticleWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RequestMapping(path = "/article")
public interface ArticleRest {

    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewArticle(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/get")
    public ResponseEntity<List<ArticleWrapper>> getAllArticle(@RequestParam(required = false) String filterValue);

    @PostMapping(path = "/update")
    public ResponseEntity<String> updateArticle(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Integer id);

    @PostMapping(path = "/updateStatus")
    public ResponseEntity<String> updateStatus(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/getByCategory/{id}")
    public ResponseEntity<List<ArticleWrapper>> getByCategory(@PathVariable Integer id);

    @GetMapping(path = "/getById/{id}")
    public ResponseEntity<ArticleWrapper> getById(@PathVariable Integer id);
}
