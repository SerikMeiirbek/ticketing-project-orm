package com.cydeo.service;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;

import java.util.List;

public interface UserService {

    List<UserDTO> listAllUsers();

    UserDTO findByUserName(String userName);

    void save(UserDTO user);

    UserDTO update(UserDTO user);

    void deleteByUserName(String username);

    void delete(String username);

}
