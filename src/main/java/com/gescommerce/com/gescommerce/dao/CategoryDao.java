package com.gescommerce.com.gescommerce.dao;

import com.gescommerce.com.gescommerce.modal.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryDao extends JpaRepository<Category, Integer> {
    List<Category> getAllCategory();
}