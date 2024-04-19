package com.gescommerce.com.gescommerce.servicelmpl;
import com.gescommerce.com.gescommerce.JWT.JwtFilter;
import com.gescommerce.com.gescommerce.constants.CommerceConstants;
import com.gescommerce.com.gescommerce.dao.CategoryDao;
import com.gescommerce.com.gescommerce.modal.Category;
import com.gescommerce.com.gescommerce.service.CategoryService;
import com.gescommerce.com.gescommerce.utils.CommerceUtils;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
        try {
            // only admins can add categories
            if (jwtFilter.isAdmin()) {
                if (validateCategoryMap(requestMap, false)) {
                    categoryDao.save(getCategoryFromMap(requestMap, false)); // add to db
                    return CommerceUtils.getResponseEntity(CommerceConstants.CATEGORY_ADDED, HttpStatus.OK);
                }
                return CommerceUtils.getResponseEntity(CommerceConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
            else {
                return CommerceUtils.getResponseEntity(CommerceConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // validId is used to distinguish between the 2 use cases -- addNewCategory and updateCategory
    private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            }
            else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    // validId is used to distinguish between the 2 use cases -- addNewCategory and updateCategory
    private Category getCategoryFromMap(Map<String, String> requestMap, boolean isAdd) {
        Category category = new Category();

        if (isAdd) {
            category.setId(Integer.parseInt(requestMap.get("id")));
        }
        category.setName(requestMap.get("name"));
        return category;
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
        try {
            // returns all categories given a filter if the filter is valid
            if (!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
                log.info("Inside if: ");
                return new ResponseEntity<List<Category>>(categoryDao.getAllCategory(), HttpStatus.OK);
            }
            // return all categories
            return new ResponseEntity<List<Category>>(categoryDao.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Category>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
        try {
            // only admins can update categories
            if (jwtFilter.isAdmin()) {
                if (validateCategoryMap(requestMap, true)) {
                    // find the specified category
                    Optional optional = categoryDao.findById(Integer.parseInt(requestMap.get("id")));
                    if (!optional.isEmpty()) {
                        categoryDao.save(getCategoryFromMap(requestMap, true)); // update to db
                        return CommerceUtils.getResponseEntity(CommerceConstants.CATEGORY_UPDATED, HttpStatus.OK);
                    }
                    else {
                        return CommerceUtils.getResponseEntity(CommerceConstants.INVALID_CATEGORY, HttpStatus.OK);
                    }
                }
                return CommerceUtils.getResponseEntity(CommerceConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
            else {
                return CommerceUtils.getResponseEntity(CommerceConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CommerceUtils.getResponseEntity(CommerceConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}