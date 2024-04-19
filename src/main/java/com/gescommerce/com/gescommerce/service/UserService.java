package com.gescommerce.com.gescommerce.service;

import com.gescommerce.com.gescommerce.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

public interface UserService {
    // user sign up api

        // Autres méthodes existantes...

    boolean isAdmin(String username);


    ResponseEntity<String> signUp(Map<String, String> requestMap);

    // user login api
    ResponseEntity<String> login(Map<String, String> requestMap);

    // user retrieval api
    ResponseEntity<List<UserWrapper>> getAllUser();

    // status update api
    ResponseEntity<String> update(Map<String, String> requestMap);

    ResponseEntity<String> checkToken();

    // change password api
    ResponseEntity<String> changePassword(Map<String, String> requestMap);

    // forgot password api
    ResponseEntity<String> forgotPassword(Map<String, String> requestMap);


}
