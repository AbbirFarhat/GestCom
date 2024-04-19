package com.gescommerce.com.gescommerce.dao;

import com.gescommerce.com.gescommerce.wrapper.UserWrapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gescommerce.com.gescommerce.modal.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByEmailId(@Param("email") String email);
    List<UserWrapper> getAllUser();
    List<String> getAllAdmin();

    @Transactional // marks the start and end of a transaction --> modifying query method
    @Modifying
    Integer updateStatus(@Param("status") String status, @Param("id") Integer id);

    User findByEmail(String email);
}
