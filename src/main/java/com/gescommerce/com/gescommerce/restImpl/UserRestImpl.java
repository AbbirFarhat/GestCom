package com.gescommerce.com.gescommerce.restImpl;

import com.gescommerce.com.gescommerce.constants.CommerceConstants;
import com.gescommerce.com.gescommerce.rest.UserRest;
import com.gescommerce.com.gescommerce.service.UserService;
import com.gescommerce.com.gescommerce.utils.CommerceUtils;
import com.gescommerce.com.gescommerce.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {
    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            return userService.login(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        try {
            return userService.getAllUser(); // retrieves all users in the management system
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
            return userService.update(requestMap); // updates the status of the users
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> checkToken() {
        try {
            return userService.checkToken(); // checks if token is valid
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try {
            return userService.changePassword(requestMap); // updates the user's password
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        try {
            return userService.forgotPassword(requestMap); // sends a reset password email to the user
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
