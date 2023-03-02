package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> listAllUsers();
    UserDTO findByUserName(String userName);
    void save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    void deleteByUserName(String userName);
    void delete(String userName);
    List<UserDTO> listAllByRole(String role);
}
