package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<User> listAllUsers() {


        return null;
    }

    @Override
    public UserDTO findByUserName(String userName) {
        return null;
    }

    @Override
    public void save(UserDTO user) {

    }

    @Override
    public UserDTO update(UserDTO user) {
        return null;
    }

    @Override
    public void deleteByUserName(String username) {

    }
}
