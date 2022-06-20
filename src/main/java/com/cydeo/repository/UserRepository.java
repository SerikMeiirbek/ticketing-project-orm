package com.cydeo.repository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    @Transactional
    void deleteByUserName(String userName);

    List<User> findAllByRoleDescriptionIgnoreCase(String description);
}
