package com.cydeo.service;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> listAllUsers();

    UserDTO findByUserName(String userName);

    void save(UserDTO user);

    UserDTO update(UserDTO user);

    void deleteByUserName(String username);

}
