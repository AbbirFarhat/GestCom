package com.gescommerce.com.gescommerce.servicelmpl;

import com.gescommerce.com.gescommerce.JWT.CustomerUsersDetailsService;
import com.gescommerce.com.gescommerce.JWT.JwtFilter;
import com.gescommerce.com.gescommerce.JWT.JwtUtil;
import com.gescommerce.com.gescommerce.constants.CommerceConstants;
import com.gescommerce.com.gescommerce.modal.User;
import com.gescommerce.com.gescommerce.dao.UserDao;
import com.gescommerce.com.gescommerce.service.UserService;
import com.gescommerce.com.gescommerce.utils.CommerceUtils;
import com.gescommerce.com.gescommerce.utils.EmailUtils;
import com.gescommerce.com.gescommerce.wrapper.UserWrapper;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUsersDetailsService customerUsersDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    EmailUtils emailUtils;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup: {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));
                // if user with the given email is not in the db, save it to the db
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));
                    return CommerceUtils.getResponseEntity(CommerceConstants.SUCCESSFULLY_REGISTERED, HttpStatus.OK);
                } else {
                    // account already exists
                    return CommerceUtils.getResponseEntity(CommerceConstants.DUPLICATE_ACCOUNT, HttpStatus.BAD_REQUEST);
                }
            } else {
                return CommerceUtils.getResponseEntity(CommerceConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // checks if sign up information is valid
    private boolean validateSignUpMap(Map<String, String> requestMap) {
        if (requestMap.containsKey("nom") && requestMap.containsKey("datedecreation")
                && requestMap.containsKey("email") && requestMap.containsKey("password")) {
            return true;
        }
        return false;
    }


    // set the values for the signup
    private User getUserFromMap(Map<String, String> requestMap) {
        User user = new User();
        user.setNom(requestMap.get("nom"));
        user.setDatedecreation(requestMap.get("datedecreation"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setProfil("user");
        return user;
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login");
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
            );

            if (auth.isAuthenticated()) {
                if (customerUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")) {
                    return new ResponseEntity<String>("{\"token\":\"" +
                            jwtUtil.generateToken(customerUsersDetailsService.getUserDetail().getEmail(),
                                    customerUsersDetailsService.getUserDetail().getProfil()) + "\"}", HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<String>("{\"message\":\""+"Please wait for admin approval."+"\"}", HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception ex) {
            log.error("{}", ex);
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.INVALID_CREDENTIALS, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        try {
            // vérifie d'abord si l'utilisateur est un admin
            if (jwtFilter.isAdmin()) {
                return new ResponseEntity<>(userDao.getAllUser(), HttpStatus.OK);
            } else {
                // renvoie une réponse non autorisée si l'utilisateur n'est pas un admin
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
            // only admins can update a user's status
            if (jwtFilter.isAdmin()) {
                Optional<User> optional = userDao.findById(Integer.parseInt(requestMap.get("id")));
                if (!optional.isEmpty()) {
                    // call update status api on the specified user
                    userDao.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                    sendMailToAllAdmin(requestMap.get("status"), optional.get().getEmail(), userDao.getAllAdmin());
                    return CommerceUtils.getResponseEntity(CommerceConstants.UPDATE_SUCCESSFUL, HttpStatus.OK);
                } else {
                    return CommerceUtils.getResponseEntity(CommerceConstants.INVALID_USER, HttpStatus.OK);
                }
            } else {
                return CommerceUtils.getResponseEntity(CommerceConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {
        allAdmin.remove(jwtFilter.getCurrentUser());
        if (status != null && status.equalsIgnoreCase("true")) {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Approved", "USER: - " + user + " \n is approved by \nADMIN: - " + jwtFilter.getCurrentUser(), allAdmin);
        } else {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Disabled", "USER: - " + user + " \n is disabled by \nADMIN: - " + jwtFilter.getCurrentUser(), allAdmin);
        }
    }

    @Override
    public ResponseEntity<String> checkToken() {
        return CommerceUtils.getResponseEntity(CommerceConstants.TRUE, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
       try {
           // find the user by their email to change their password
            User userObject = userDao.findByEmail(jwtFilter.getCurrentUser());
            if (!userObject.equals(null)) {
                // entered correct old password --> can change to new password
                if (userObject.getPassword().equals(requestMap.get("oldPassword"))) {
                    userObject.setPassword(requestMap.get("newPassword"));
                    userDao.save(userObject); // save the data to the db
                    return CommerceUtils.getResponseEntity(CommerceConstants.PASSWORD_CHANGED, HttpStatus.OK);
                }
                return CommerceUtils.getResponseEntity(CommerceConstants.INCORRECT_OLD_PASSWORD, HttpStatus.BAD_REQUEST);
            }
            return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
       } catch (Exception ex) {
           ex.printStackTrace();
       }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        try {
            User userObject = userDao.findByEmail(requestMap.get("email"));
            if (!Objects.isNull(userObject) && !Strings.isNullOrEmpty(userObject.getEmail())) {
                // Utilisez l'injection d dépendance (autowiring) pour obtenir l'objet EmailUtils
                emailUtils.forgotMail(userObject.getEmail(), "Reset your Password", userObject.getPassword());
            }
            return CommerceUtils.getResponseEntity(CommerceConstants.CHECK_EMAIL, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    public boolean isAdmin(String username) {
        User user = userDao.findByEmailId(username);
        return user != null && "admin".equals(user.getProfil());
    }


}
